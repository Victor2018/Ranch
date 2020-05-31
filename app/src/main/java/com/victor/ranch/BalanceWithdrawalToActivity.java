package com.victor.ranch;


import android.os.Bundle;
import android.view.View;

import com.victor.ranch.ui.dialog.WithdrawalConfirmDialog;

import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BalanceWithdrawalToActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class BalanceWithdrawalToActivity extends BaseActivity {


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_balance_withdrawal_to;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
    }

    private void initData () {
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }

    @OnClick(R.id.btn_next)
    public void OnWithdrawalClick(View v) {
        BalanceWithdrawalActivity.intentStart(this,BalanceWithdrawalActivity.class);
        finish();
    }

}
