package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.R;
import com.victor.ranch.WebActivity;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.MyOrderAdapter;
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
public class MyOrderFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.rv_my_order)
    LMRecyclerView mRvMyOrder;

    private MyOrderAdapter mMyOrderAdapter;

    public static MyOrderFragment newInstance () {
        MyOrderFragment fragment = new MyOrderFragment();
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
        return R.layout.frag_my_order;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mMyOrderAdapter = new MyOrderAdapter(getActivity(),this);
        mMyOrderAdapter.setHeaderVisible(false);
        mMyOrderAdapter.setFooterVisible(false);
        mMyOrderAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvMyOrder.setAdapter(mMyOrderAdapter);
    }

    private void initData () {
        for (int i=0;i<8;i++) {
            mMyOrderAdapter.add(new NewsInfo());
        }
        mMyOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WebActivity.intentStart(getContext(),"咨询中心","https://www.baidu.com/");
    }
}
