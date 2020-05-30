package com.victor.ranch.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
 * File: FeedBackDialog
 * Author: Victor
 * Date: 2020/5/29 下午 05:53
 * Description:
 * -----------------------------------------------------------------
 */
public class FeedBackDialog extends AbsDialog {

    @Bind(R.id.et_message)
    EditText mEtMessage;

    public FeedBackDialog(Context context) {
        super(context);
    }

    @Override
    public void handleLayoutParams(WindowManager.LayoutParams wlp) {
        wlp.width = (int) (DeviceUtils.getDisplayMetrics().widthPixels * 0.83);
    }

    @Override
    protected int bindContentView() {
        return R.layout.dlg_feed_back;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.tv_ok)
    public void OnOkClick (View v) {
        dismiss();
    }

    @OnClick(R.id.tv_cancel)
    public void OnCancelClick (View v) {
        dismiss();
    }



    @Override
    public void onDestroy() {

    }
}
