package com.smart.frame.ui.view.recycleview.refresh;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Zijin on 2017/7/14.
 */

public interface ILoadMore {
    /**
     * 设置是否可以加载更多
     */
    void setLoadMoreEnable(boolean enable);

    /**
     * 返回当前是否可以加载更多
     */
    boolean isLoadMoreEnable();

    /**
     * 判断是否正在加载
     */
    boolean isLoading();

    /**
     * 设置视图状态
     */
    void setStatus(Status status);

    /**
     * 状态发生改变
     */
    void onStatusChange(Status status);

    /**
     * 设置加载更多监听
     */
    void setOnLoadMoreListener(OnLoadMoreListener listener);

    /**
     * 获取布局对应的RecycleView
     */
    RecyclerView getRecycleView();
}
