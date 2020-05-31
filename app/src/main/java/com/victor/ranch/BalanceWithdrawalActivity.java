package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.AccountDetailAdapter;
import com.victor.ranch.ui.dialog.WithdrawalConfirmDialog;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BalanceWithdrawalActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class BalanceWithdrawalActivity extends BaseActivity {


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_balance_withdrawal;
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

    @OnClick(R.id.btn_withdrawal)
    public void OnWithdrawalClick(View v) {
        WithdrawalConfirmDialog withdrawalConfirmDialog = new WithdrawalConfirmDialog(this);
        withdrawalConfirmDialog.show();
    }

}
