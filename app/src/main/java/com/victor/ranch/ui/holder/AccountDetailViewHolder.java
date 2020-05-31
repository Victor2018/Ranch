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
 * File: AccountDetailViewHolder
 * Author: Victor
 * Date: 2020/4/1 上午 11:08
 * Description:
 * -----------------------------------------------------------------
 */
public class AccountDetailViewHolder extends ContentViewHolder implements View.OnClickListener {
    public static final int ONITEM_CLICK = 0;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public TextView mTvTitle,mTvAmt;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public AccountDetailViewHolder(View view) {
        super(view);
        mTvTitle = itemView.findViewById(R.id.tv_title);
        mTvAmt = itemView.findViewById(R.id.tv_amt);

        view.setOnClickListener(this);
    }

    public void bindData(NewsInfo data) {
        mTvAmt.setText(getAdapterPosition() %2 == 0 ? "+100.00" : "-50.00" );
        mTvAmt.setTextColor(getAdapterPosition() %2 == 0 ? ResUtils.getColorRes(R.color.color_03C79B):
                ResUtils.getColorRes(R.color.color_DC1919));

    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, view, getAdapterPosition(), 0);
        }
    }
}
