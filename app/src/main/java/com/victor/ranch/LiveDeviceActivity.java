package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.LiveDeviceAdapter;
import com.victor.ranch.ui.adapter.ProductListAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LiveDeviceActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class LiveDeviceActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.rv_device)
    LMRecyclerView mRvDevice;

    private LiveDeviceAdapter mLiveDeviceAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_live_device;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mLiveDeviceAdapter = new LiveDeviceAdapter(this,this);
        mLiveDeviceAdapter.setHeaderVisible(false);
        mLiveDeviceAdapter.setFooterVisible(false);
        mLiveDeviceAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvDevice.setAdapter(mLiveDeviceAdapter);
    }

    private void initData () {
        for (int i=0;i<6;i++) {
            mLiveDeviceAdapter.add(new NewsInfo());
        }
        mLiveDeviceAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
