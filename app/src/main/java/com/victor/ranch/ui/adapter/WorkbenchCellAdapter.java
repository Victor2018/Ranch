package com.victor.ranch.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.ranch.R;
import com.victor.ranch.data.WorkbenchCellData;
import com.victor.ranch.interfaces.OnWorkbenchCellClickListener;
import com.victor.ranch.util.ImageUtils;
import com.victor.ranch.util.ResUtils;

import java.util.List;

public class WorkbenchCellAdapter extends RecyclerView.Adapter<WorkbenchCellAdapter.ViewHolder> {
    private String TAG = "HomeCellAdapter";
    private Context mContext;
    private List<WorkbenchCellData> datas;
    private OnWorkbenchCellClickListener mOnWorkbenchCellClickListener;

    public WorkbenchCellAdapter(Context context,
                                List<WorkbenchCellData> datas, OnWorkbenchCellClickListener listener) {
        mContext = context;
        this.datas = datas;
        mOnWorkbenchCellClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WorkbenchCellData data = datas.get(position);
        holder.nameTextView.setText(data.title);
        ImageUtils.get().loadImage(mContext,holder.imageView, ResUtils.getMipmapId(data.image));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView mTvPlayProgress;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            mTvPlayProgress = itemView.findViewById(R.id.tv_play_progress);
        }

        @Override
        public void onClick(View v) {
            if (datas != null && datas.size() > 0) {
                int position = getAdapterPosition();
                if (position >= 0 && position < datas.size()) {
                    Log.d(TAG, datas.get(getAdapterPosition()).title);
                    if (mOnWorkbenchCellClickListener != null) {
                        mOnWorkbenchCellClickListener.OnWorkbenchCelClick(datas.get(position));
                    }
                }
            }
        }
    }

}

