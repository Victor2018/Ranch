package com.victor.ranch.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hwangjr.rxbus.RxBus;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.victor.ranch.R;
import com.victor.ranch.app.App;
import com.victor.ranch.util.AppConfig;
import com.victor.ranch.util.BusActions;
import com.victor.ranch.util.JsonUtils;
import com.victor.ranch.util.Loger;


/**
 * 微信授权
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private static final String SHARE_WECHAT = "CONTANT_SHARE:wechat";

	private static final String SHARE_FRIENDS = "CONTANT_SHARE:friends";

	/**
	 * 微信API
	 */
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, AppConfig.WECHAT_APP_ID, false);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		setIntent(intent);
		api.handleIntent(intent, this);
	}

	/**
	 * 微信发送请求到第三方应用时,会回调到该方法
	 *
	 * @param req
	 */
	@Override
	public void onReq(BaseReq req) {
		Loger.d(this.getClass().getSimpleName(),"onReq()......");
	}

	/**
	 * 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	 *
	 * @param resp
	 */
	@Override
	public void onResp(BaseResp resp) {
		Loger.d(this.getClass().getSimpleName(), "onResp()-resp = " + JsonUtils.toJSONString(resp));
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				RxBus.get().post(BusActions.LOGIN_WX_AUTH_ERROR, App.get().getString(R.string.user_cancelled_operation));
				break;
			case BaseResp.ErrCode.ERR_OK:
				if (resp instanceof SendAuth.Resp) {
					Loger.d(this.getClass().getSimpleName(), "onResp()-LOGIN_AUTH_WEIXIN" + resp);
					//微信登陆
					RxBus.get().post(BusActions.LOGIN_AUTH_WEIXIN, resp);
				} else if (resp instanceof SendMessageToWX.Resp) {
					Loger.d(this.getClass().getSimpleName(), "onResp()-SHARE_WECHAT_RESULT" + resp);
					//分享返回
					RxBus.get().post(BusActions.SHARE_WECHAT_RESULT, resp);
				}
				break;
		}
		finish();
	}
}