package com.victor.ranch.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
/**
 * Created by victor on 2017/11/23.
 */

public class LMRecyclerView extends RecyclerView {
    private String TAG = "LMRecyclerView";
    private boolean isScrollingToBottom = true;

    public static final int LINEAR = 0;
    public static final int GRID = 1;
    public static final int STAGGERED_GRID = 2;

    //标识RecyclerView的LayoutManager是哪种
    protected int layoutManagerType;
    // 瀑布流的最后一个的位置
    protected int[] lastPositions;
    // 最后一个的位置
    protected int lastVisibleItem;

    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean hasMore = true;
    private int headerCount = 1;

    public LMRecyclerView(Context context) {
        super(context);
    }

    public LMRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LMRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface OnLoadMoreListener {
        void OnLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mOnLoadMoreListener = loadMoreListener;
    }


    @Override
    public void onScrolled(int dx, int dy) {
        isScrollingToBottom = dy > 0;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            layoutManagerType = LINEAR;
        } else if (layoutManager instanceof GridLayoutManager) {
            layoutManagerType = GRID;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            layoutManagerType = STAGGERED_GRID;
        } else {
            throw new RuntimeException(
                    "Unsupported LayoutManager used. Valid ones are " +
                            "LinearLayoutManager, GridLayoutManager and " +
                            "StaggeredGridLayoutManager");
        }

        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItem = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItem = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItem = findMax(lastPositions);
                break;
        }
    }


    @Override
    public void onScrollStateChanged(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int totalItemCount = getLayoutManager().getItemCount();
            Log.e(TAG,"lastVisibleItem------>" + lastVisibleItem);
            Log.e(TAG,"totalItemCount------->" + totalItemCount);

            if (lastVisibleItem + 1 + headerCount >= totalItemCount && mOnLoadMoreListener != null && hasMore) {
                Log.e(TAG,"LOAD MORE DATA......");
                mOnLoadMoreListener.OnLoadMore();//回调加载更多监听
            }
        }

    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void setHasMore (boolean hasMore) {
        this.hasMore = hasMore;
        headerCount = hasMore ? 1 : 0;
    }

}
