package com.victor.ranch.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.victor.ranch.R;
import com.victor.ranch.util.CallPhoneUtil;
import com.victor.ranch.util.DeviceUtils;
import com.victor.ranch.util.ResUtils;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CustomerServiceDialog
 * Author: Victor
 * Date: 2020/5/29 下午 05:53
 * Description:
 * -----------------------------------------------------------------
 */
public class CustomerServiceDialog extends AbsDialog {

    @Bind(R.id.tv_message)
    TextView mTvMessage;

    public CustomerServiceDialog(Context context) {
        super(context);
    }

    @Override
    public void handleLayoutParams(WindowManager.LayoutParams wlp) {
        wlp.width = (int) (DeviceUtils.getDisplayMetrics().widthPixels * 0.83);
    }

    @Override
    protected int bindContentView() {
        return R.layout.dlg_customer_service;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSpannable();
    }

    @OnClick(R.id.tv_ok)
    public void OnOkClick (View v) {
        CallPhoneUtil.callPhoneManual(mContext,ResUtils.getStringRes(R.string.consumer_hotline));
        dismiss();
    }

    @OnClick(R.id.tv_cancel)
    public void OnCancelClick (View v) {
        dismiss();
    }

    private void setSpannable() {
        String tip = ResUtils.getStringRes(R.string.call_customer_service);
        String input = ResUtils.getStringRes(R.string.consumer_hotline);

        tip = String.format(tip,input);

        int mColorBase = ResUtils.getColorRes(R.color.color_03C89B);
        int mSearchCount = getWordCount(input);

        SpannableString spannableString = new SpannableString(tip);
        int index = 0;
        while (index != -1) {
            index = tip.indexOf(input, index);
            if (index == -1)
                break;
            spannableString.setSpan(new ForegroundColorSpan(mColorBase), index, index = (index + mSearchCount), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        mTvMessage.setText(spannableString);
    }

    public int getWordCount(String s) {
        s = s.replaceAll("[\\u4e00-\\u9fa5]", "*");
        int length = s.length();
        return length;
    }

    @Override
    public void onDestroy() {

    }
}
