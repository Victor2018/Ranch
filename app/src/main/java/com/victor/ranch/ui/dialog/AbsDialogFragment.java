package com.victor.ranch.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.victor.ranch.R;
import com.victor.ranch.interfaces.IDialogRecycle;

import butterknife.ButterKnife;


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AbsDialogFragment.java
 * Author: Victor
 * Date: 2019/3/22 14:05
 * Description: DialogFragment基类
 * -----------------------------------------------------------------
 */

public abstract class AbsDialogFragment extends DialogFragment {

    protected static final String TAG = "AbsDialogFragment";

    protected View mRootView;

    protected Context mContext;

    protected abstract int bindContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BaseNoTitleDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(bindContentView(), container);
        initView(mRootView);
        return mRootView;
    }

    protected void initView(View rootView) {

    }

    /**
     * 用于处理窗口的属性
     *
     * @param window
     */
    protected void handleWindow(Window window) {

    }

    /**
     * 处理默认的宽和高
     * 和动画效果
     *
     * @param wl
     */
    protected void handleLayoutParams(WindowManager.LayoutParams wl) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, mRootView);
        Window window = getDialog().getWindow();
        //处理默认窗口属性
        handleWindow(window);
        //设置属性信息宽高或者动画
        WindowManager.LayoutParams wlp = window.getAttributes();
        handleLayoutParams(wlp);
        window.setAttributes(wlp);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //避免,重复加载报错
        manager.executePendingTransactions();
        //提交
        if (manager.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
