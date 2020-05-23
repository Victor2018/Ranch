package com.victor.ranch.util;

import com.victor.ranch.BuildConfig;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by longtv, All rights reserved.
 * -----------------------------------------------------------------
 * File: AppConfig.java
 * Author: Victor
 * Date: 2018/8/13 15:26
 * Description:
 * -----------------------------------------------------------------
 */
public class AppConfig {
    /**
     * 开发者模式
     */
    public static final boolean MODEL_DEBUG = BuildConfig.MODEL_DEBUG;
    /**
     * 线上模式
     */
    public static final boolean MODEL_ONLINE = BuildConfig.MODEL_ONLINE;

    /**
     * 编译版本
     */
    public static final int BUILD_CODE = BuildConfig.BUILD_CODE;

    /**
     * QQ KEY
     */
    public static final String QQ_APP_ID = "1110405869";

    /**
     * 微信AppID
     */
    public static final String WECHAT_APP_ID = "wx53a643474c93da3f";

    public static final String UMENG_APP_KEY = "5eb2ade5167edd828d0000a4";

    public static final String UMENG_MESSAGE_SECRET = "b1eb2372a6aab2ce90ec65b7cdfaf110";
    public static final String SCHEMA = "ranch://";
}
