package com.victor.ranch.module.pay.interfaces;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OnPlayCompleteListener
 * Author: Victor
 * Date: 2020/4/21 上午 11:39
 * Description: 支付结果回调
 * -----------------------------------------------------------------
 */
public interface OnPayCompleteListener {
    void OnPlayComplete(String msg, boolean isPaySuccess);
}
