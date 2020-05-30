package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.ui.dialog.CheckUpdateDialog;
import com.victor.ranch.ui.dialog.FeedBackDialog;

import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AccountCancellationActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class AccountCancellationActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_account_cancellation;
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


    @OnClick(R.id.btn_account_cancellation)
    public void OnAccountCancellationClick(View v) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
