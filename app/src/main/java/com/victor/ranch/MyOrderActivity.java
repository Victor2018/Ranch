package com.victor.ranch;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.victor.ranch.ui.adapter.ViewPagerAdapter;
import com.victor.ranch.ui.fragment.MyOrderFragment;
import com.victor.ranch.ui.fragment.NewsCenterFragment;
import com.victor.ranch.util.AppUtil;

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
public class MyOrderActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private final static String PAGE_INDEX_KEY = "PAGE_INDEX_KEY";
    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private int pageIndex;

    public static void intentStart(Context ctx, int pageIndex) {
        if (ctx == null) return;
        Intent intent = new Intent(ctx, MyOrderActivity.class);
        intent.putExtra(PAGE_INDEX_KEY,pageIndex);
        AppUtil.launchApp(ctx, intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initialize();
    }

    private void initialize () {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.setDatas(titleList);
        mViewPagerAdapter.setFrags(fragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(pageIndex);
    }

    private void initData () {
        pageIndex = getIntent().getIntExtra(PAGE_INDEX_KEY,0);
        String[] titles = getResources().getStringArray(R.array.my_order_title);
        for (int i=0;i<titles.length;i++) {
            fragmentList.add(MyOrderFragment.newInstance());
            titleList.add(titles[i]);
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
