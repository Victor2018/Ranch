package com.victor.ranch.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.victor.ranch.R;
import com.victor.ranch.interfaces.IDialogRecycle;

import butterknife.ButterKnife;


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LoadingDialog
 * Author: Victor
 * Date: 2020/4/16 上午 10:10
 * Description: 普通对话框
 * -----------------------------------------------------------------
 */

public abstract class AbsDialog extends Dialog implements IDialogRecycle {

    protected Context mContext;

    protected abstract int bindContentView();

    public AbsDialog(Context context) {
        this(context, R.style.BaseNoTitleDialog);
    }

    public AbsDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getContext();
        setContentView(bindContentView());

        ButterKnife.bind(this);

        //设置属性信息宽高或者动画
        Window window = getWindow();
        handleWindow(window);
        WindowManager.LayoutParams wlp = window.getAttributes();
        handleLayoutParams(wlp);
        window.setAttributes(wlp);
    }

    /**
     * 用于处理窗口的属性
     *
     * @param window
     */
    protected void handleWindow(Window window) {

    }

    public void handleLayoutParams(WindowManager.LayoutParams wlp) {

    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);
    }
}
