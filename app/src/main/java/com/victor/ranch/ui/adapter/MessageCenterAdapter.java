package com.victor.ranch.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.victor.ranch.R;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.holder.MessageCenterViewHolder;
import com.victor.ranch.ui.holder.NewsViewHolder;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MessageCenterAdapter
 * Author: Victor
 * Date: 2020/5/29 上午 08:31
 * Description:
 * -----------------------------------------------------------------
 */
public class MessageCenterAdapter extends BaseRecycleAdapter<NewsInfo, RecyclerView.ViewHolder> {
    public MessageCenterAdapter(Context context, AdapterView.OnItemClickListener listener) {
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
        View rootView = getLayoutView(R.layout.rv_message_center_cell,parent);
        return new MessageCenterViewHolder(rootView);
    }

    public View getLayoutView(int resource,ViewGroup parent) {
        final View rootView = mLayoutInflater.inflate(resource, parent,false);
        return rootView;
    }

    @Override
    public void onBindContentVHolder(RecyclerView.ViewHolder viewHolder, NewsInfo data, int position) {
        MessageCenterViewHolder holder = (MessageCenterViewHolder) viewHolder;
        holder.bindData(data);
        holder.setOnItemClickListener(mOnItemClickListener);

    }

    @Override
    public int getContentItemViewType(int position) {
        return ITEM_TYPE_CONTENT;
    }

}
