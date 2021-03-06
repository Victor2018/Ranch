package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.MessageCenterAdapter;
import com.victor.ranch.ui.dialog.CheckUpdateDialog;
import com.victor.ranch.ui.dialog.FeedBackDialog;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SettingActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class SettingActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_setting;
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

    @OnClick(R.id.tv_check_update)
    public void OnCheckUpdateClick(View v) {
        CheckUpdateDialog checkUpdateDialog = new CheckUpdateDialog(this);
        checkUpdateDialog.show();
    }

    @OnClick(R.id.tv_message_notification)
    public void OnMessageNotificationClick(View v) {
        MessageNotificationActivity.intentStart(this,MessageNotificationActivity.class);
    }

    @OnClick(R.id.tv_feedback)
    public void OnFeedBackClick(View v) {
        FeedBackDialog feedBackDialog = new FeedBackDialog(this);
        feedBackDialog.show();
    }

    @OnClick(R.id.tv_account_cancellation)
    public void OnAccountCancellationClick(View v) {
        AccountCancellationActivity.intentStart(this,AccountCancellationActivity.class);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
