package com.victor.ranch.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.victor.ranch.R;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AbsPopWindow
 * Author: Victor
 * Date: 2020/4/23 上午 11:18
 * Description: popwindow 基类
 * -----------------------------------------------------------------
 */
public abstract class AbsPopWindow extends PopupWindow {
    protected Context mContext;
    protected abstract int bindContentView();
    protected abstract double getWeightPercentage();
    protected abstract double getHeightPercentage();
    protected abstract void initView(View view);

    private int mWidth;
    private int mHeight;

    public AbsPopWindow(Context context) {
        mContext = context;
        handleWindow();
        initialzie();
    }

    private void handleWindow() {
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mWidth = (int) (width * getWeightPercentage());
        mHeight = (int) (height * getHeightPercentage());
        //设置popwindow弹出窗体的宽
        setWidth(mWidth > 0 ? mWidth : WindowManager.LayoutParams.WRAP_CONTENT);
        //设置popwindow弹出窗体的高
        setHeight(mHeight > 0 ? mHeight : WindowManager.LayoutParams.WRAP_CONTENT);
        //设置popwindow弹出窗体可点击
        setFocusable(true);
        setOutsideTouchable(true);
        //刷新状态
        update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable cd = new ColorDrawable(0000000000);
        //点击BACK键和其他地方使其消失，设置了这个才能触发OnDismissListener，设置其他控件变化等操作
        setBackgroundDrawable(cd);
        setAnimationStyle(R.style.BasePopWindowStyle);
    }

    private void initialzie () {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(bindContentView(),null);
        initView(view);
        setContentView(view);
    }


    public void show(View anchor){
        show(anchor,0, 0);
    }

    public void show (View anchor, LocationGravity gravity) {
        show(anchor,gravity,0, 0);
    }
    public void show (View anchor, int xOff, int yOff) {
        show(anchor, LocationGravity.LEFT_BOTTOM,xOff,yOff);
    }

    public void show (View anchor, LocationGravity gravity, int xOff, int yOff) {

        getContentView().measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = getContentView().getMeasuredWidth();
        int height = getContentView().getMeasuredHeight();

        int[] locations = new int[2];
        anchor.getLocationOnScreen(locations);
        int left = locations[0];
        int top  =  locations[1];

        switch (gravity) {
            case TOP_LEFT:
                showAtLocation(anchor, Gravity.NO_GRAVITY,left - width + xOff,
                        top - height + yOff);
                break;
            case TOP_CENTER:
                int offsetX = (anchor.getWidth() - width) / 2;
                showAtLocation(anchor, Gravity.NO_GRAVITY,left + offsetX + xOff,
                        top - height + yOff);
                break;
            case TOP_RIGHT:
                showAtLocation(anchor, Gravity.NO_GRAVITY,left + anchor.getWidth() + xOff,
                        top - height + yOff);
                break;
            case BOTTOM_LEFT:
                showAsDropDown(anchor, -width + xOff,yOff);
                break;
            case BOTTOM_CENTER:
                int offsetX1 = (anchor.getWidth() - height) / 2;
                showAsDropDown(anchor,offsetX1 + xOff,yOff);
                break;
            case BOTTOM_RIGHT:
                showAsDropDown(anchor, anchor.getWidth() + xOff,yOff);
                break;
            case LEFT_TOP:
                showAtLocation(anchor, Gravity.NO_GRAVITY, left - width + xOff,
                        top - height + yOff);
                break;
            case LEFT_BOTTOM:
                showAtLocation(anchor, Gravity.NO_GRAVITY, left - width + xOff,
                        top + anchor.getHeight() + yOff);
                break;
            case LEFT_CENTER:
                int offsetY = (anchor.getHeight() - height) / 2;
                showAtLocation(anchor, Gravity.NO_GRAVITY,left - width + xOff,top + offsetY + yOff);
                break;

            case RIGHT_TOP:
                showAtLocation(anchor, Gravity.NO_GRAVITY, left + anchor.getWidth() + xOff,
                        top - height + yOff);
                break;
            case RIGHT_BOTTOM:
                showAtLocation(anchor, Gravity.NO_GRAVITY, left + anchor.getWidth() + xOff,
                        top + anchor.getHeight() + yOff);
                break;
            case RIGHT_CENTER:
                int offsetY1 = (anchor.getHeight() - height) / 2;
                showAtLocation(anchor, Gravity.NO_GRAVITY,left + anchor.getWidth() + xOff,
                        top + offsetY1 + yOff);
                break;
            case FROM_BOTTOM:
                showAtLocation(anchor, Gravity.BOTTOM,xOff,yOff);
                break;
        }
    }


    public enum LocationGravity {
        TOP_LEFT,
        TOP_RIGHT,
        TOP_CENTER,

        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_CENTER,

        RIGHT_TOP,
        RIGHT_BOTTOM,
        RIGHT_CENTER,

        LEFT_TOP,
        LEFT_BOTTOM,
        LEFT_CENTER,

        FROM_BOTTOM,
    }
}
