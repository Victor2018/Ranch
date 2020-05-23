package com.victor.ranch.module.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.victor.okhttp.library.module.MainHandler;
import com.victor.ranch.app.App;
import com.victor.ranch.module.pay.alipay.data.PayResult;
import com.victor.ranch.module.pay.interfaces.OnPayCompleteListener;
import com.victor.ranch.module.pay.wxpay.WxPayRes;
import com.victor.ranch.util.AppConfig;
import com.victor.ranch.util.AppUtil;
import com.victor.ranch.util.BusActions;
import com.victor.ranch.util.JsonUtils;
import com.victor.ranch.util.Loger;

import java.util.HashMap;
import java.util.Map;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: PayHelper
 * Author: Victor
 * Date: 2020/4/21 上午 11:07
 * Description: 微信，支付宝支付模块
 * -----------------------------------------------------------------
 */
public class PayHelper {
    public static final int ALI_PAY = 0x101;
    public static final int WX_PAY = 0x102;

    private String TAG = "PayHelper";
    private Activity mActivity;
    private Handler mRechargeHandler;
    private HandlerThread mRechargeHandlerThread;
    private OnPayCompleteListener mOnPayCompleteListener;
    private boolean isRxBusRegister = false;

    private IWXAPI mIWxApi;

    public PayHelper (Activity activity, OnPayCompleteListener listener) {
        mActivity = activity;
        mOnPayCompleteListener = listener;
        init();
    }

    private void init () {
        if (!isRxBusRegister) {
            RxBus.get().register(this);
            isRxBusRegister = true;
        }

        startPayTask();
    }

    private void startPayTask (){
        mRechargeHandlerThread = new HandlerThread("rechargeTask");
        mRechargeHandlerThread.start();
        mRechargeHandler = new Handler(mRechargeHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ALI_PAY:
                        HashMap<Integer, Object> aliPayMap = (HashMap<Integer, Object>) msg.obj;
                        String orderInfo = (String) aliPayMap.get(ALI_PAY);
                        aliPay(mActivity,orderInfo);
                        break;
                    case WX_PAY:
                        HashMap<Integer, Object> wxPayMap = (HashMap<Integer, Object>) msg.obj;
                        WxPayRes wxPayRes = (WxPayRes) wxPayMap.get(WX_PAY);
                        wxPay(wxPayRes);
                        break;
                }
            }
        };
    }

    public void sendPayRequestWithParms (int action, Object requestData) {
        HashMap<Integer, Object> requestMap = new HashMap<>();
        requestMap.put(action, requestData);
        Message msg = mRechargeHandler.obtainMessage(action,requestMap);
        mRechargeHandler.sendMessage(msg);
    }

    public void onDestroy (){
        if (mRechargeHandlerThread != null) {
            mRechargeHandlerThread.quit();
            mRechargeHandlerThread = null;
        }
        RxBus.get().unregister(this);
        isRxBusRegister = false;
    }

    /**
     * 0 支付成功
     * -1 发生错误 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     * -2 用户取消 发生场景：用户不支付了，点击取消，返回APP。
     */
    @Subscribe(tags = {@Tag(BusActions.EVENT_WXPAY_RESULT)})
    public void onWxPaySuccess(PayResp result) {

        switch (result.errCode) {
            case 0:
                onPayResult("支付成功",true);
                break;
            case -1:
                onPayResult("支付异常",false);
                break;
            case -2:
                onPayResult("用户中途取消支付",false);
                break;
            default:
                onPayResult("支付失败",false);
                break;
        }
    }


    /**支付宝支付
     * @param activity
     * @param orderInfo 服务器返回的经过加签验证的字符串码(orderInfo 的获取必须来源于服务端)
     */
    private void aliPay (Activity activity, String orderInfo) {
        PayTask alipay = new PayTask(activity);
        Map<String, String> result = alipay.payV2(orderInfo, true);
        PayResult payResult = new PayResult(result);

        /**
         * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            onPayResult("支付成功-" + resultInfo,true);
        } else if (TextUtils.equals(resultStatus,"8000")) {
            onPayResult("正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态",false);
        } else if (TextUtils.equals(resultStatus,"4000")) {
            onPayResult("订单支付失败",false);
        } else if (TextUtils.equals(resultStatus,"5000")) {
            onPayResult("重复请求",false);
        } else if (TextUtils.equals(resultStatus,"6001")) {
            onPayResult("用户中途取消支付",false);
        } else if (TextUtils.equals(resultStatus,"6004")) {
            onPayResult("网络连接出错",false);
        } else if (TextUtils.equals(resultStatus,"6002")) {
            onPayResult("支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态",false);
        } else {
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            onPayResult("支付失败",false);
        }
    }

    /**
     * 微信支付
     */
    private void wxPay(WxPayRes bean) {
        Loger.e(TAG,"wxPay()......bean = " + JsonUtils.toJSONString(bean));
        if (TextUtils.isEmpty(bean.appid)) {
            bean.appid = AppConfig.WECHAT_APP_ID;
        }
        mIWxApi = getIWXAPI();
        if (AppUtil.isWXAppInstalledAndSupported(mIWxApi)) {
            PayReq req = new PayReq();
            req.appId = bean.appid;
            req.partnerId = bean.partnerId;
            req.prepayId = bean.prepayId;
            req.nonceStr = bean.nonceStr;
            req.timeStamp = bean.timestamp;
            req.packageValue = bean.packageValue;
            req.sign = bean.sign;
            mIWxApi.sendReq(req);
        }
    }

    private IWXAPI getIWXAPI() {
        if (mIWxApi == null) {
            mIWxApi = WXAPIFactory.createWXAPI(App.get(), AppConfig.WECHAT_APP_ID, true);
            mIWxApi.registerApp(AppConfig.WECHAT_APP_ID);
        }
        return mIWxApi;
    }

    /**
     * 处理返回结果
     * @param msg
     * @param isPaySuccess
     */
    private void onPayResult(final String msg, final boolean isPaySuccess) {
        MainHandler.runMainThread(new Runnable() {
            @Override
            public void run() {
                if (mOnPayCompleteListener != null) {
                    mOnPayCompleteListener.OnPlayComplete(msg,isPaySuccess);
                }
            }
        });
    }

}
