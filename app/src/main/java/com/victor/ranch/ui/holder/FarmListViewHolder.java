package com.victor.ranch.ui.holder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.victor.ranch.R;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.util.ResUtils;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: FarmListViewHolder
 * Author: Victor
 * Date: 2020/4/1 上午 11:08
 * Description:
 * -----------------------------------------------------------------
 */
public class FarmListViewHolder extends ContentViewHolder implements View.OnClickListener {
    public static final int ONITEM_CLICK = 0;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public TextView mTvTitle,mTvStatus;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public FarmListViewHolder(View view) {
        super(view);
        mTvTitle = itemView.findViewById(R.id.tv_title);
        mTvStatus = itemView.findViewById(R.id.tv_status);

        view.setOnClickListener(this);
    }

    public void bindData(NewsInfo data) {
        mTvStatus.setText(getAdapterPosition() % 2 == 0 ? "已审核" : "未审核");
        mTvStatus.setBackgroundResource(getAdapterPosition() % 2 == 0 ? R.drawable.bg_farm_reviewed :
                R.drawable.bg_farm_unreviewed);

    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), 0);
        }
    }
}
