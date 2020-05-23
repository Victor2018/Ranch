package com.victor.ranch.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.victor.ranch.R;


/**
 * Created by Administrator on 2017/11/24.
 */

public class BottomViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;
    public TextView mTvTitle,mTvEnd;
    public LinearLayout mLayoutEnd;

    public BottomViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        mTvEnd = (TextView) itemView.findViewById(R.id.tv_end);
        mLayoutEnd = (LinearLayout) itemView.findViewById(R.id.ll_end);
    }

    public void setEndTextColor (int colorId) {
        mTvEnd.setTextColor(colorId);
    }
}
