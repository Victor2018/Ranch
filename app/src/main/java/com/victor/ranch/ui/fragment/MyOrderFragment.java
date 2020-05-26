package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.victor.ranch.R;
import com.victor.ranch.util.Constant;

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
public class MyOrderFragment extends BaseFragment {

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
    }
}
