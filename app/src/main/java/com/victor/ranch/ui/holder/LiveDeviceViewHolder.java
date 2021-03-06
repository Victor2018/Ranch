package com.victor.ranch.ui.holder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.victor.ranch.R;
import com.victor.ranch.data.NewsInfo;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LiveDeviceViewHolder
 * Author: Victor
 * Date: 2020/4/1 上午 11:08
 * Description:
 * -----------------------------------------------------------------
 */
public class LiveDeviceViewHolder extends ContentViewHolder implements View.OnClickListener {
    public static final int ONITEM_CLICK = 0;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public TextView mTvTitle;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public LiveDeviceViewHolder(View view) {
        super(view);
        mTvTitle = itemView.findViewById(R.id.tv_title);

        view.setOnClickListener(this);
    }

    public void bindData(NewsInfo data) {

    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), 0);
        }
    }
}
