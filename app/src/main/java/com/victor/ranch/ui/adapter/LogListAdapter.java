package com.victor.ranch.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.victor.ranch.R;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.holder.LogListViewHolder;
import com.victor.ranch.ui.holder.ProductListViewHolder;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: LogListAdapter
 * Author: Victor
 * Date: 2020/5/29 上午 08:31
 * Description:
 * -----------------------------------------------------------------
 */
public class LogListAdapter extends BaseRecycleAdapter<NewsInfo, RecyclerView.ViewHolder> {
    public LogListAdapter(Context context, AdapterView.OnItemClickListener listener) {
        super(context,listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeadVHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindHeadVHolder(RecyclerView.ViewHolder viewHolder, NewsInfo data, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateContentVHolder(ViewGroup parent, int viewType) {
        View rootView = getLayoutView(R.layout.rv_log_list_cell,parent);
        return new LogListViewHolder(rootView);
    }

    public View getLayoutView(int resource,ViewGroup parent) {
        final View rootView = mLayoutInflater.inflate(resource, parent,false);
        return rootView;
    }

    @Override
    public void onBindContentVHolder(RecyclerView.ViewHolder viewHolder, NewsInfo data, int position) {
        LogListViewHolder holder = (LogListViewHolder) viewHolder;
        holder.bindData(data);
        holder.setOnItemClickListener(mOnItemClickListener);

    }

    @Override
    public int getContentItemViewType(int position) {
        return ITEM_TYPE_CONTENT;
    }

}
