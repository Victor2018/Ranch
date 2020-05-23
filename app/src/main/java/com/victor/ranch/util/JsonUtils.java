package com.victor.ranch.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;
import java.util.List;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: JsonUtils
 * Author: Victor
 * Date: 2020/3/31 上午 09:58
 * Description:
 * -----------------------------------------------------------------
 */

public class JsonUtils {

    /**
     * 日志标签
     */
    private static final String TAG = "JsonUtils";

    public static String toJSONString(Object object) {
            String json = JSON.toJSONString(object);
        return json;
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        try {
            T result = JSON.parseObject(text, clazz);//mGson.fromJson(text, clazz);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseObject(String text, Type clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (JSONException e) {
        }
        return null;
    }

    public static <T> T parseObject(String text, TypeReference<T> clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

}
