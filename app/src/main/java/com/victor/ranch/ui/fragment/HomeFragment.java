package com.victor.ranch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;

import com.victor.ranch.MainActivity;
import com.victor.ranch.R;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HomeFragment
 * Author: Victor
 * Date: 2020/5/27 上午 08:57
 * Description:
 * -----------------------------------------------------------------
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.nsv_content)
    NestedScrollView mNsvContent;

    @Bind(R.id.rv_messge)
    LMRecyclerView mRvMessage;


    public static HomeFragment newInstance () {
        HomeFragment fragment = new HomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.INTENT_DATA_KEY,data);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize () {
        //取消recycleview的滑动
//        mRvMessage.setHasFixedSize(true);
//        mRvMessage.setNestedScrollingEnabled(false);

        mNsvContent.post(new Runnable() {
            @Override
            public void run() {
                mNsvContent.smoothScrollTo(0, 0);
            }
        });
        mRvMessage.post(new Runnable() {
            @Override
            public void run() {
                mRvMessage.smoothScrollToPosition(0);
            }
        });


    }

    @Override
    public boolean handleBackEvent() {
        return false;
    }

    @Override
    public void freshFragData() {

    }

}
