package com.victor.ranch.ui.holder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.victor.ranch.R;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.util.ResUtils;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MyOrderViewHolder
 * Author: Victor
 * Date: 2020/4/1 上午 11:08
 * Description:
 * -----------------------------------------------------------------
 */
public class MyOrderViewHolder extends ContentViewHolder implements View.OnClickListener {
    public static final int ONITEM_CLICK = 0;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public TextView mTvTitle,mTvStatus;
    private Button mBtnOrderType;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public MyOrderViewHolder(View view) {
        super(view);
        mTvTitle = itemView.findViewById(R.id.tv_title);
        mTvStatus = itemView.findViewById(R.id.tv_status);
        mBtnOrderType = itemView.findViewById(R.id.btn_order_type);

        view.setOnClickListener(this);
    }

    public void bindData(NewsInfo data) {
        if (getAdapterPosition() == 0) {
            mTvStatus.setTextColor(ResUtils.getColorRes(R.color.color_FBAA53));
            mTvStatus.setBackgroundResource(R.drawable.bg_yellow_border_radius_15);
            mTvStatus.setText("养殖中");
            mBtnOrderType.setText("特殊情况（关闭订单）");

        } else if (getAdapterPosition() == 1) {
            mTvStatus.setTextColor(ResUtils.getColorRes(R.color.color_03C89B));
            mTvStatus.setBackgroundResource(R.drawable.bg_green_border_radius_15);
            mTvStatus.setText("到期处理");
            mBtnOrderType.setText("屠宰订单");
        } else if (getAdapterPosition() == 2) {
            mTvStatus.setTextColor(ResUtils.getColorRes(R.color.color_03C89B));
            mTvStatus.setBackgroundResource(R.drawable.bg_green_border_radius_15);
            mTvStatus.setText("到期处理");
            mBtnOrderType.setText("线下订单");
        } else if (getAdapterPosition() == 3) {
            mTvStatus.setTextColor(ResUtils.getColorRes(R.color.white));
            mTvStatus.setBackgroundResource(R.drawable.bg_green_shape_radius_15);
            mTvStatus.setText("已完成");
            mBtnOrderType.setVisibility(View.GONE);
        } else if (getAdapterPosition() == 4) {
            mTvStatus.setTextColor(ResUtils.getColorRes(R.color.white));
            mTvStatus.setBackgroundResource(R.drawable.bg_red_shape_radius_15);
            mTvStatus.setText("已关闭");
            mBtnOrderType.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), 0);
        }
    }
}
