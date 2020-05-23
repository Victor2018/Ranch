package com.victor.ranch.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: DateUtils
 * Author: Victor
 * Date: 2020/4/8 上午 11:38
 * Description:
 * -----------------------------------------------------------------
 */
public class DateUtils {
    private static final String TAG = "DateUtils";

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String transDate (String inputDate, String inputFormat, String outFormat) {
        String date = "";
        if (TextUtils.isEmpty(inputDate)) return date;
        SimpleDateFormat format = new SimpleDateFormat(inputFormat);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(inputDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Loger.e(TAG,"inputDate = " + inputDate);
        SimpleDateFormat formatter = new SimpleDateFormat(outFormat);
        date = formatter.format(c.getTime());
        Loger.e(TAG,"date = " + date);
        return date;
    }

    /**
     * 获取当前时间的年
     * @return
     */
    public static String getNowYear() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String today = formatter.format(date);
        return today;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取今天日期
     * @return
     */
    public static String getTodayDate () {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd");
        String today = formatter.format(date);
        Loger.e(TAG, "getDateOfToday-today = " + today);
        return today;
    }

    /**
     * @param m 分钟差值
     * @return
     */
    public static String ms2CreateTime (long m) {
        Loger.e(TAG,"ms2ActiveTime-min = " + m);
        String res = "";
        long day = 0;
        long hour = 0;
        long min = 0;
        day = m / (24 * 60);
        hour = (m / 60 - day * 24);
        min = (m - day * 24 * 60 - hour * 60);

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (min > 0) {
            sb.append(min + "分钟");
        }
        res = sb.toString();
        Loger.e(TAG,"ms2ActiveTime-res = " + res);
        return res;

    }
    /**
     * @param ms 时间戳差值
     * @return
     */
    public static String ms2SysCreateTime (long ms) {
        Loger.e(TAG,"ms2ActiveTime-ms = " + ms);
        String res = "";
        long day = 0;
        long hour = 0;
        long min = 0;
        day = ms / (24 * 60 * 60 * 1000);
        hour = (ms / 60 - day * 24);
        min = (ms - day * 24 * 60 - hour * 60);

        if (day > 0) {
            res = day + "天前";
        }
        if (hour > 0) {
            res = hour + "小时前";
        }
        if (min > 0) {
            res = min + "分钟前";
        }
        Loger.e(TAG,"ms2SysCreateTime-res = " + res);
        return res;

    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getTimestamp () {
        return System.currentTimeMillis();
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s){
        Loger.e(TAG,"dateToStamp-s = " + s);
        long timeStamp = 0;
        if (TextUtils.isEmpty(s)) return timeStamp;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(s);
            timeStamp = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeStamp;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return
     */
    public static boolean isToday (String day) {
        try {
            Calendar pre = Calendar.getInstance();
            Date predate = new Date(System.currentTimeMillis());
            pre.setTime(predate);
            Calendar cal = Calendar.getInstance();
            Date date = getDateFormat().parse(day);
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
                int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                        - pre.get(Calendar.DAY_OF_YEAR);
                if (diffDay == 0) {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

}
