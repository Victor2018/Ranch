package com.victor.ranch.module.pay.wxpay;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: WxPayRes
 * Author: Victor
 * Date: 2020/4/21 下午 03:31
 * Description:
 * -----------------------------------------------------------------
 */
public class WxPayRes {
    public String appid;// APPID
    public String partnerId;//商户ID
    public String prepayId;//预支付订单ID
    public String nonceStr;//微信支付特有接口
    public String timestamp;//时间戳
    public String sign;//签名
    public String packageValue;
    public String recordNo;//内部的订单id
}
