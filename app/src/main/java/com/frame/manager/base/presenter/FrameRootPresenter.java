package com.frame.manager.base.presenter;

import android.util.Log;

import com.frame.data.entity.Repo;
import com.frame.manager.ApiService;
import com.frame.manager.base.contracts.IContracts;
import com.frame.manager.constants.Constants;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zijin on 2017/7/20.
 * Email:info@zijinqianbao.com
 */

public class FrameRootPresenter<V extends IContracts.IView> implements IContracts.IPresenter {
    private static final String TAG = "FrameRootPresenter";

    private WeakReference<V> mWeakReference;
    private ApiService mApiService;

    /**
     * 默认构造方法，无需请求网络
     */
    public FrameRootPresenter(V v) {
        mWeakReference = new WeakReference<>(v);
    }

    /**
     * 构造方法，需要请求网络
     * {@link com.frame.di.ApiServiceModule}
     */
    public FrameRootPresenter(V v, ApiService apiService) {
        mWeakReference = new WeakReference<>(v);
        this.mApiService = apiService;
    }

    public V getView() {
        if (mWeakReference != null) {
            return mWeakReference.get();
        }

        return null;
    }

    /**
     * 获取网络请求ApiService
     */
    public ApiService getApiService() {
        return mApiService;
    }

    @Override
    public <T> void request(Observable<Repo<T>> observable) {
        observable.throttleFirst(Constants.THROTTLE_DELAY, TimeUnit.MILLISECONDS)
                .timeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .compose(getView().bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Repo<T>>() {
                    @Override
                    public void onNext(Repo<T> simpleRepo) {
                        Log.d(TAG, "onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        getView().showNetError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
