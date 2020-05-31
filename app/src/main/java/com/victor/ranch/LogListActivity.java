package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.LogListAdapter;
import com.victor.ranch.ui.adapter.ProductListAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LogListActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class LogListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.rv_log)
    LMRecyclerView mRvLog;

    private LogListAdapter mLogListAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_log_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mLogListAdapter = new LogListAdapter(this,this);
        mLogListAdapter.setHeaderVisible(false);
        mLogListAdapter.setFooterVisible(false);
        mLogListAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvLog.setAdapter(mLogListAdapter);
    }

    private void initData () {
        for (int i=0;i<6;i++) {
            mLogListAdapter.add(new NewsInfo());
        }
        mLogListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
