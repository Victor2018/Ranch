package com.victor.ranch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BaseFragmentPagerAdapter
 * Author: Victor
 * Date: 2020/5/27 上午 08:54
 * Description:
 * -----------------------------------------------------------------
 */
public class BaseFragmentPagerAdapter  extends FragmentPagerAdapter {
    private List<Fragment> frags;

    public void setFrags(List<Fragment> frags) {
        this.frags = frags;
    }

    public List<Fragment> getFrags() {
        return frags;
    }

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }
}
