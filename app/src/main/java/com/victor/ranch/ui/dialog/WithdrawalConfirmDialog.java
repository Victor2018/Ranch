package com.victor.ranch.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.victor.ranch.R;
import com.victor.ranch.util.DeviceUtils;

import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: WithdrawalConfirmDialog
 * Author: Victor
 * Date: 2020/5/29 下午 05:53
 * Description:
 * -----------------------------------------------------------------
 */
public class WithdrawalConfirmDialog extends AbsDialog {


    public WithdrawalConfirmDialog(Context context) {
        super(context);
    }

    @Override
    public void handleLayoutParams(WindowManager.LayoutParams wlp) {
        wlp.width = (int) (DeviceUtils.getDisplayMetrics().widthPixels * 0.83);
    }

    @Override
    protected int bindContentView() {
        return R.layout.dlg_withdrawal_confirm;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_confirm)
    public void OnOkClick (View v) {
        WithdrawalSuccessDialog withdrawalSuccessDialog = new WithdrawalSuccessDialog (mContext);
        withdrawalSuccessDialog.show();
        dismiss();
    }



    @Override
    public void onDestroy() {

    }
}
