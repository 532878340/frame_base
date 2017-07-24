package com.frame.manager.base.presenter;

import android.support.annotation.NonNull;

import com.frame.data.entity.Repo;
import com.frame.manager.ApiService;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.callback.CallBack;
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
    protected ApiService getApiService() {
        return mApiService;
    }

    @Override
    public <T> void performRequest(Observable<Repo<T>> observable, final RequestFlag flag, @NonNull CallBack<T> callBack) {
        observable.throttleFirst(Constants.THROTTLE_DELAY, TimeUnit.MILLISECONDS)
                .timeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .compose(getView().bindToLife())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    switch (flag) {
                        case FLAG_INIT:
                            getView().initLoading();
                            break;
                        case FLAG_DIALOG:
                            getView().showLoading();
                            break;
                        default:
                            break;
                    }
                })
                .subscribe(new DefaultObserver<Repo<T>>() {
                    @Override
                    public void onNext(Repo<T> repo) {
                        switch (flag) {
                            case FLAG_INIT:
                                if (repo.isOk()) {
                                    getView().hideLoading();
                                    callBack.onRequestSuccess(repo, flag);
                                } else {
                                    getView().showNetError();
                                }
                                break;
                            case FLAG_REFRESH:
                                getView().finishLoading();
                            case FLAG_DIALOG:
                                getView().hideLoading();
                            default:
                                if (repo.isOk()) {
                                    callBack.onRequestSuccess(repo, flag);
                                } else {
                                    getView().showMessage(repo.getDescription());
                                    callBack.onRequestIllegal(repo, flag);
                                }
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag) {
                            case FLAG_INIT:
                                getView().showNetError();
                                break;
                            case FLAG_REFRESH:
                                getView().finishLoading();
                            case FLAG_DIALOG:
                                getView().hideLoading();
                            default:
                                break;
                        }
                        callBack.onRequestFailed(flag);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
