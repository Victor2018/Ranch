package com.victor.ranch;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.victor.ranch.ui.adapter.MyOrderViewPagerAdapter;
import com.victor.ranch.ui.fragment.NewsCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MyOrderActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class NewsCenterActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private MyOrderViewPagerAdapter mMyOrderViewPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_center;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initialize();
    }

    private void initialize () {
        mMyOrderViewPagerAdapter = new MyOrderViewPagerAdapter(getSupportFragmentManager());
        mMyOrderViewPagerAdapter.setDatas(titleList);
        mMyOrderViewPagerAdapter.setFrags(fragmentList);
        mViewPager.setAdapter(mMyOrderViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOnPageChangeListener(this);

    }

    private void initData () {
        for (int i=0;i<8;i++) {
            fragmentList.add(NewsCenterFragment.newInstance());
            titleList.add("测试" + i);
        }
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick (View v) {
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
