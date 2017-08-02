package com.frame.manager.base.presenter;

import android.support.annotation.NonNull;

import com.frame.data.entity.Repo;
import com.frame.manager.ApiService;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.callback.CallBack;
import com.frame.manager.base.callback.HttpResultObserver;
import com.frame.manager.base.contracts.IContracts;
import com.frame.manager.constants.Constants;
import com.frame.manager.utils.TransformUtils;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

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

    /**
     * FLAG_REFRESH 请求
     */
    protected <T> void performRefresh(@NonNull CallBack<T> callBack) {
        performRequest(RequestFlag.FLAG_REFRESH, callBack);
    }

    /**
     * FLAG_DIALOG 请求
     */
    protected <T> void performLoading(@NonNull CallBack<T> callBack) {
        performRequest(RequestFlag.FLAG_DIALOG, callBack);
    }

    @Override
    public <T> Observable<Repo<T>> getObservable() {
        return null;
    }

    @Override
    public Map<String, String> getRequestMap() {
        return null;
    }

    @Override
    public <T> void performRequest(final RequestFlag flag, @NonNull CallBack<T> callBack) {
        Observable<Repo<T>> observable = getObservable();

        if(getObservable() == null){
            return;
        }

        observable.throttleFirst(Constants.THROTTLE_DELAY, TimeUnit.MILLISECONDS)
                .timeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .compose(getView().bindToLife())
                .compose(TransformUtils.defaultScheduler())
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
                .subscribe(new HttpResultObserver<T>() {
                    @Override
                    public void onSuccess(Repo<T> repo) {
                        switch (flag) {
                            case FLAG_INIT:
                            case FLAG_DIALOG:
                                getView().hideLoading();
                                break;
                            case FLAG_REFRESH:
                                getView().finishLoading();
                                break;
                            default:
                                break;
                        }

                        callBack.onSuccess(repo);
                    }

                    @Override
                    public void onIllegal(Repo<T> repo) {
                        getView().showMessage(repo.getDescription());
                        switch (flag) {
                            case FLAG_INIT:
                                getView().showNetError();
                                break;
                            case FLAG_DIALOG:
                                getView().hideLoading();
                                break;
                            case FLAG_REFRESH:
                                getView().finishLoading();
                                break;
                            default:
                                break;
                        }

                        callBack.onIllegal(repo);
                    }

                    @Override
                    public void onFailed() {
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
                        callBack.onFailed();
                    }
                });
    }
}
