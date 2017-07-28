package com.frame.view.recycleview;

import android.view.View;

public interface OnItemClickListener{
    /**
     * 点击事件
     */
    void onItemClick(View view, int position);

    /**
     * 长按
     */
    boolean onItemLongClick(View view, int position);
}
