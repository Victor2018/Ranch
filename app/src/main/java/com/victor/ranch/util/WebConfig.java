package com.victor.ranch.util;

import android.net.Uri;

import com.victor.ranch.app.App;

import java.util.HashMap;

/**
 * Created by victor on 2017/10/23.
 */

public class WebConfig {
    public static final int PAGE_SIZE = 20;

    public static final String DEBUG_BASE_URL    = "http://demo.longott.com/v%d/";
    public static final String ONLINE_BASE_URL  = "http://ott.long.tv/v%d/";

    private static String getServer (){
        if (AppConfig.MODEL_ONLINE) {
            return String.format(ONLINE_BASE_URL,1);
        }
        return String.format(DEBUG_BASE_URL,1);
    }

    public static String getServerIp () {
        Uri uri = Uri.parse(getServer());
        String serverIp = uri.getHost();
        return serverIp;
    }

    public static String getRequestUrl (String api) {
        return getServer() + api;
    }

    public static HashMap<String,String> getHttpHeaderParm () {
        String token = "";
        /*LoginReq loginReq =  App.get().getLoginReq();
        if (loginReq != null) {
            token = loginReq.token;
        }*/

        if (token == null) {
            token = "";
        }

        HashMap<String,String> header = new HashMap<>();
        header.put("X-token",token);
        header.put("X-version", AppUtil.getAppVersionName(App.get()));
        header.put("X-deviceType","phone");
        header.put("X-mac", DeviceUtils.getMac());
//        header.put("X-mac", "f4911e03f20c");
        if (AppConfig.MODEL_DEBUG) {
            header.put("X-mac", "64bc0c50c437");
        }
        header.put("X-areaCode","");//
        return header;
    }
}
