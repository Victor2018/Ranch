package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.MessageCenterAdapter;
import com.victor.ranch.ui.adapter.NewsAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CustomerServiceActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class MessageCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.rv_messge)
    LMRecyclerView mRvMessage;

    private MessageCenterAdapter mMessageCenterAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_message_center;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mMessageCenterAdapter = new MessageCenterAdapter(this,this);
        mMessageCenterAdapter.setHeaderVisible(false);
        mMessageCenterAdapter.setFooterVisible(false);
        mMessageCenterAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvMessage.setAdapter(mMessageCenterAdapter);
    }

    private void initData () {
        for (int i=0;i<6;i++) {
            mMessageCenterAdapter.add(new NewsInfo());
        }
        mMessageCenterAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
