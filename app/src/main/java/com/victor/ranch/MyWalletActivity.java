package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.AccountDetailAdapter;
import com.victor.ranch.ui.adapter.MessageCenterAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
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
public class MyWalletActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.rv_account_detail)
    LMRecyclerView mRvAccountDetail;

    private AccountDetailAdapter mAccountDetailAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mAccountDetailAdapter = new AccountDetailAdapter(this,this);
        mAccountDetailAdapter.setHeaderVisible(false);
        mAccountDetailAdapter.setFooterVisible(false);
        mAccountDetailAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvAccountDetail.setAdapter(mAccountDetailAdapter);
    }

    private void initData () {
        for (int i=0;i<28;i++) {
            mAccountDetailAdapter.add(new NewsInfo());
        }
        mAccountDetailAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }

    @OnClick(R.id.tv_withdrawal)
    public void OnWithdrawalClick(View v) {
        BalanceWithdrawalToActivity.intentStart(this,BalanceWithdrawalToActivity.class);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
