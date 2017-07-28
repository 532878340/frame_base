package com.frame.view.recycleview.refresh;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Zijin on 2017/7/14.
 */

public interface IRecycleView {
    /**
     * 设置布局管理器
     */
    void setLayoutManager(RecyclerView.LayoutManager layoutManager);

    /**
     * 设置ItemDecoration
     */
    void addItemDecoration(RecyclerView.ItemDecoration decor);

    /**
     *  设置适配器
     */
    void setAdapter(RecyclerView.Adapter adapter);

    /**
     * 获取适配器
     */
    RecyclerView.Adapter getAdapter();

    /**
     * 获取布局管理器
     */
    RecyclerView.LayoutManager getLayoutManager();
}
