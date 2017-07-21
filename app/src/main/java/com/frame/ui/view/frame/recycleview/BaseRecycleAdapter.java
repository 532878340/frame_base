package com.frame.ui.view.frame.recycleview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zijin on 2017/7/13.
 * RecycleView适配器
 */

public abstract class BaseRecycleAdapter<E> extends RecyclerView.Adapter<BaseRecycleHolder> implements IRecycleAdapter<E>{

    protected Context mContext;
    /**
     * 数据集合
     */
    private List<E> mData = new ArrayList<>();
    /**
     * 布局加载器
     */
    protected LayoutInflater mInflater;
    /**
     * 布局id
     */
    private int mLayoutId;
    /**
     * 点击事件监听
     */
    private OnItemClickListener mOnItemClickListener;


    public BaseRecycleAdapter(Context context, @NonNull List<E> list, @LayoutRes int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;

        if(list != null){
            mData.addAll(list);
        }
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutId,parent,false);
        return new BaseRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleHolder holder, final int position) {
        holder.itemView.setOnClickListener(view -> {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view,position);
            }
        });

        holder.itemView.setOnLongClickListener(view -> mOnItemClickListener != null && mOnItemClickListener.onItemLongClick(view,position));

        onBindView(holder,position,mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /*********************** 适配器相关操作 *************************/

    @NonNull
    @Override
    public List<E> getAdapterData() {
        return mData;
    }

    @Override
    public void add(@NonNull E e) {
        mData.add(e);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(@NonNull List<E> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void remove(@NonNull E t) {
        mData.remove(t);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(@NonNull List<E> list) {
        mData.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
