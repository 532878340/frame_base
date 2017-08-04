package com.smart.frame.base.presenter;

import com.smart.frame.base.contract.IMVPContract;

import java.lang.ref.WeakReference;

/**
 * Description: Presenter基类
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class BasePresenter<V extends IMVPContract.IBaseView> implements IMVPContract.IBasePresenter<V>{
    /**
     * 弱引用 view
     */
    private WeakReference<V> mWeakReference;

    public V getView() {
        if (mWeakReference != null) {
            return mWeakReference.get();
        }

        return null;
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
