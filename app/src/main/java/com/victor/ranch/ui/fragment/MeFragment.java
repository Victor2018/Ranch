package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.victor.ranch.R;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MyFragment
 * Author: Victor
 * Date: 2020/5/27 上午 09:00
 * Description:
 * -----------------------------------------------------------------
 */
public class MeFragment extends BaseFragment {
    public static MeFragment newInstance () {
        MeFragment fragment = new MeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.INTENT_DATA_KEY,data);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_me;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean handleBackEvent() {
        return false;
    }

    @Override
    public void freshFragData() {

    }
}
