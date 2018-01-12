package com.smart.frame.ui.view.basic.recycleview;

import android.view.View;

/**
 * @author Gjm
 */
public interface OnItemClickListener{
    /**
     * 点击事件
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * 长按
     * @param view
     * @param position
     * @return
     */
    boolean onItemLongClick(View view, int position);
}
