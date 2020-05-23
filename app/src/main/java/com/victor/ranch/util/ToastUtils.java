package com.victor.ranch.util;

import android.support.annotation.IntegerRes;
import android.widget.Toast;

import com.victor.ranch.app.App;

/**
 * 吐司工具类
 */

public class ToastUtils {

    /**
     * 调试模式下可显示
     *
     * @param msg
     */
    public static void showDebug(String msg) {
        if (AppConfig.MODEL_DEBUG) {
            Toast.makeText(App.get(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 调试模式下可显示
     *
     * @param resId
     */
    public static void showDebug(@IntegerRes int resId) {
        if (AppConfig.MODEL_DEBUG) {
            final String text = ResUtils.getStringRes(resId);
            Toast.makeText(App.get(), text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短暂显示
     *
     * @param msg
     */
    public static void showShort(CharSequence msg) {
        Toast.makeText(App.get(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    public static void showShort(int resId) {
        final String text = ResUtils.getStringRes(resId);
        Toast.makeText(App.get(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示
     *
     * @param msg
     */
    public static void showLong(CharSequence msg) {
        Toast.makeText(App.get(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    public static void showLong(int resId) {
        final String text = ResUtils.getStringRes(resId);
        Toast.makeText(App.get(), text, Toast.LENGTH_LONG).show();
    }

}
