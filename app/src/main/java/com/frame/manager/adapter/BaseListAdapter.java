package com.frame.manager.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * adapter父类
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    protected LayoutInflater mInflater;
    protected Context mContext;
    private int mLayoutId;

    public BaseListAdapter(Context context, List<T> list, @LayoutRes int resId) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
        mLayoutId = resId;
    }

    public void addData(T t) {
        this.mList.add(t);
        notifyDataSetChanged();
    }

    public void addData(@NonNull List<T> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mList;
    }

    public void removeData(List<T> list) {
        this.mList.removeAll(list);
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutId, parent, false);
        }

        convertView(position, convertView, getItem(position));

        return convertView;
    }

    /**
     * item处理
     *
     * @param view
     * @param type
     */
    protected abstract void convertView(int position, View view, T type);
}
