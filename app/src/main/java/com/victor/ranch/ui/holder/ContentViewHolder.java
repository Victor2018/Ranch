package com.victor.ranch.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Victor on 2017/11/24.
 */

public class ContentViewHolder extends RecyclerView.ViewHolder {
    public AdapterView.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public ContentViewHolder(View view) {
        super(view);
    }

}
