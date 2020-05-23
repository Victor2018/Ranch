package com.victor.ranch.util;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BusActions
 * Author: Victor
 * Date: 2020/3/31 上午 09:58
 * Description:
 * -----------------------------------------------------------------
 */

public interface BusActions {

    /**
     * 微信第三方登录
     */
    String LOGIN_WX_AUTH_ERROR = "LOGIN_WX_AUTH_ERROR";

    /**
     * 微信第三方登录
     */
    String LOGIN_AUTH_WEIXIN = "LOGIN_AUTH_WEIXIN";

    /**
     * 微信分享
     */
    String SHARE_WECHAT_RESULT = "SHARE_WECHAT_RESULT";


    /**
     * 微信支付成功回调
     */
    String EVENT_WXPAY_RESULT = "EVENT_WXPAY_RESULT";
    /**
     * 支付宝支付成功回调
     */
    String EVENT_ALIPAY_RESULT = "EVENT_ALIPAY_RESULT";



}
