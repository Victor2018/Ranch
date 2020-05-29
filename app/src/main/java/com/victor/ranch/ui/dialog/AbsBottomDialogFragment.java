package com.victor.ranch.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.victor.ranch.R;


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AbsBottomDialogFragment.java
 * Author: Victor
 * Date: 2019/3/22 14:05
 * Description: DialogFragment基类
 * -----------------------------------------------------------------
 */

public abstract class AbsBottomDialogFragment extends AbsDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initView(View rootView) {

    }

    /**
     * 用于处理窗口的属性
     *
     * @param window
     */
    protected void handleWindow(Window window) {
        //底部弹出
        window.setGravity(Gravity.BOTTOM);
    }

    /**
     * 处理默认的宽和高
     * 和动画效果
     *
     * @param wl
     */
    protected void handleLayoutParams(WindowManager.LayoutParams wl) {
        wl.windowAnimations = R.style.BottomDialogAnimShow;
    }
}
