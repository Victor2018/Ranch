package com.victor.ranch.ui.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.victor.ranch.R;
import com.victor.ranch.data.WorkbenchData;
import com.victor.ranch.interfaces.OnWorkbenchCellClickListener;
import com.victor.ranch.ui.widget.GravitySnapHelper;

import java.util.List;

public class WorkbenchAdapter extends RecyclerView.Adapter<WorkbenchAdapter.ViewHolder> implements GravitySnapHelper.SnapListener {
    private Context mContext;
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    private List<WorkbenchData> datas;
    private OnWorkbenchCellClickListener mOnWorkbenchCellClickListener;

    public void setDatas (List<WorkbenchData> datas) {
        this.datas = datas;
    }
    public List<WorkbenchData> getDatas () {
        return datas;
    }
    public WorkbenchAdapter(Context context, OnWorkbenchCellClickListener listener) {
        mContext = context;
        mOnWorkbenchCellClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return HORIZONTAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_snap, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WorkbenchData snap = datas.get(position);
        holder.snapTextView.setText(snap.category_name);

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder
                .recyclerView.getContext(), snap.gravity = LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setOnFlingListener(null);
        new LinearSnapHelper().attachToRecyclerView(holder.recyclerView);


        holder.recyclerView.setAdapter(new WorkbenchCellAdapter(mContext, snap.content,mOnWorkbenchCellClickListener));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onSnap(int position) {
        Log.d("Snapped: ", position + "");
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView snapTextView;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            snapTextView = itemView.findViewById(R.id.snapTextView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            //解决父view 垂直recyclerView与CoordinatorLayout 联动问题
            //此横向RecyclerView的父View是纵向RecyclerView，而RecyclerView只实现了NestedScrollingChild，
            // 无法像CoordinatorLayout一样响应。所以要关闭横向RecyclerView的嵌套滑动功能，
            // 让横向RecyclerView如同其他嵌入纵向RecyclerView的view一样，触发折叠。
            //链接：简书https://www.jianshu.com/p/ddbe6a4095d3
            recyclerView.setNestedScrollingEnabled(false);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (datas != null && datas.size() > 0) {
                if (position >= 0 && position < datas.size()) {
                }
            }
        }
    }
}

