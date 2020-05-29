package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.R;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.NewsAdapter;
import com.victor.ranch.ui.adapter.NewsCenterAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MyOrderFragment
 * Author: Victor
 * Date: 2020/5/25 上午 10:42
 * Description:
 * -----------------------------------------------------------------
 */
public class NewsCenterFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.rv_news_center)
    LMRecyclerView mRvNewsCenter;

    private NewsCenterAdapter mNewsCenterAdapter;

    public static NewsCenterFragment newInstance () {
        NewsCenterFragment fragment = new NewsCenterFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.INTENT_DATA_KEY,data);
//        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public boolean handleBackEvent() {
        return false;
    }

    @Override
    public void freshFragData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_news_center;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mNewsCenterAdapter = new NewsCenterAdapter(getActivity(),this);
        mNewsCenterAdapter.setHeaderVisible(false);
        mNewsCenterAdapter.setFooterVisible(false);
        mNewsCenterAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvNewsCenter.setAdapter(mNewsCenterAdapter);
    }

    private void initData () {
        for (int i=0;i<8;i++) {
            mNewsCenterAdapter.add(new NewsInfo());
        }
        mNewsCenterAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
