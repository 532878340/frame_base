package com.func.ui.view.frame.recycleview.refresh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.frame.R;

/**
 * Created by Zijin on 2017/7/14.
 * Email:info@zijinqianbao.com
 */

public class RecycleLoadLayout extends RelativeLayout implements ILoadMore,IRecycleView{
    private RecyclerView mRecycleView;
    /**
     * 底部加载布局
     */
    private View mFooterView;
    /**
     * 当前状态
     */
    private Status mStatus = Status.STATUS_NO_LOADING;
    /**
     * 加载更多监听
     */
    private OnLoadMoreListener mLoadMoreListener;
    /**
     * 是否可以加载更多
     */
    private boolean mLoadMoreEnable;

    /**
     * 布局管理器
     */
    private RecyclerView.LayoutManager mLayoutManager;

    public RecycleLoadLayout(Context context) {
        this(context, null);
    }

    public RecycleLoadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecycleLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.layout_recycle_loadmore, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mRecycleView = findViewById(R.id.recycleView);
        mFooterView = findViewById(R.id.footerView);

        mRecycleView.addOnScrollListener(new OnScrollListener() {
            private int mLastVisiblePosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //滑动到底部
                if(newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisiblePosition + 1 == mLayoutManager.getItemCount()){
                    if(!isLoading() && isLoadMoreEnable()){
                        setStatus(Status.STATUS_LOADING);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(mLayoutManager instanceof LinearLayoutManager){//线性布局
                    mLastVisiblePosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                }else if(mLayoutManager instanceof GridLayoutManager){//Grid布局
                    mLastVisiblePosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                }else if(mLayoutManager instanceof StaggeredGridLayoutManager){//流式布局
                    int[] positions = new int[((StaggeredGridLayoutManager) mLayoutManager).getSpanCount()];
                    ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(positions);
                    mLastVisiblePosition = findMax(positions);
                }
            }
        });
    }

    @Override
    public void setLoadMoreEnable(boolean enable) {
        mLoadMoreEnable = enable;
    }

    @Override
    public boolean isLoadMoreEnable() {
        return mLoadMoreEnable;
    }

    @Override
    public boolean isLoading() {
        return mStatus == Status.STATUS_LOADING;
    }

    @Override
    public void setStatus(Status status) {
        if(mStatus == status){
            return;
        }

        mStatus = status;
        onStatusChange(status);
    }

    @Override
    public void onStatusChange(Status status) {
        switch (status){
            case STATUS_LOADING://正在加载
                mFooterView.setVisibility(View.VISIBLE);
                if(mLoadMoreListener != null){
                    mLoadMoreListener.onLoadMore();
                }
                break;
            case STATUS_NO_LOADING://未加载
                mFooterView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    @Override
    public RecyclerView getRecycleView() {
        return mRecycleView;
    }

    @Override
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mRecycleView.setLayoutManager(layoutManager);
    }

    @Override
    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecycleView.addItemDecoration(decor);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return mRecycleView.getAdapter();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecycleView.getLayoutManager();
    }

    /**
     * 获取最大值
     */
    private int findMax(int[] positions){
        int max = positions[0];
        for (int value : positions){
            if(max < value){
                max = value;
            }
        }
        return max;
    }
}
