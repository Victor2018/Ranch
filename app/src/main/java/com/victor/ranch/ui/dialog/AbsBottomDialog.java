package com.victor.ranch.ui.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.victor.ranch.R;
import com.victor.ranch.util.DeviceUtils;


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AbsBottomDialog.java
 * Author: Victor
 * Date: 2019/3/22 14:05
 * Description:
 * -----------------------------------------------------------------
 */

public abstract class AbsBottomDialog extends AbsDialog {

    public AbsBottomDialog(Context context) {
        super(context);
    }

    @Override
    protected void handleWindow(Window window) {
        //底部弹出
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void handleLayoutParams(WindowManager.LayoutParams wlp) {
        wlp.width = DeviceUtils.getDisplayMetrics().widthPixels;
        wlp.windowAnimations = R.style.BottomDialogAnimShow;
    }
}
