package com.smart.frame.base.presenter;

import com.smart.frame.base.contract.IBasePresenter;
import com.smart.frame.base.contract.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Presenter基类
 * @author Gjm
 * @date 2018/01/12
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    /**
     * 弱引用 view
     */
    private WeakReference<V> mWeakReference;

    protected V mView;

    public V getView() {
        if (mWeakReference != null) {
            mView = mWeakReference.get();
        }

        return mView;
    }

    @Override
    public void attach(V view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public void detach() {
        if(mWeakReference != null){
            mWeakReference.clear();
            mWeakReference = null;
        }
    }
}
