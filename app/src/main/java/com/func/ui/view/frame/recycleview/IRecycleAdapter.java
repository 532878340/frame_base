package com.func.ui.view.frame.recycleview;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Zijin on 2017/7/13.
 * RecycleAdapter相关操作
 */

public interface IRecycleAdapter<E> {
    /**
     * 添加单条数据
     */
    void add(@NonNull E t);

    /**
     * 添加集合数据
     */
    void addAll(@NonNull List<E> list);

    /**
     * 移除单条数据
     */
    void remove(@NonNull E t);

    /**
     * 移除集合数据
     */
    void removeAll(@NonNull List<E> list);

    /**
     * 返回集合数据
     */
    @NonNull
    List<E> getAdapterData();

    /**
     * 清除集合
     */
    void clear();

    /**
     * 设置点击监听
     */
    void setOnItemClickListener(OnItemClickListener onItemClickListener);

    /**
     * 视图绑定
     */
    void onBindView(BaseRecycleHolder holder, final int position, E e);
}
