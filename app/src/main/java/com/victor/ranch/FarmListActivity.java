package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.FarmListAdapter;
import com.victor.ranch.ui.adapter.MessageCenterAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FarmListActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class FarmListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.rv_farm)
    LMRecyclerView mRvFarm;

    private FarmListAdapter mFarmListAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_farm_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mFarmListAdapter = new FarmListAdapter(this,this);
        mFarmListAdapter.setHeaderVisible(false);
        mFarmListAdapter.setFooterVisible(false);
        mFarmListAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvFarm.setAdapter(mFarmListAdapter);
    }

    private void initData () {
        for (int i=0;i<6;i++) {
            mFarmListAdapter.add(new NewsInfo());
        }
        mFarmListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
