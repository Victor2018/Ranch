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
 * File: NewsViewHolder
 * Author: Victor
 * Date: 2020/4/1 上午 11:08
 * Description:
 * -----------------------------------------------------------------
 */
public class NewsViewHolder extends ContentViewHolder implements View.OnClickListener {
    public static final int ONITEM_CLICK = 0;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public TextView mTvTitle,mTvMessage;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public NewsViewHolder(View view) {
        super(view);
        mTvTitle = itemView.findViewById(R.id.tv_title);
        mTvMessage = itemView.findViewById(R.id.tv_message);

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
