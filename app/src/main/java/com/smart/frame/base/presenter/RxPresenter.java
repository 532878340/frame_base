package com.smart.frame.base.presenter;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.DataManager;
import com.smart.frame.utils.TransformUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 处理rx相关的presenter
 *
 * @author Gjm
 * @date 2018/01/12
 */
public class RxPresenter<V extends IBaseView> extends BasePresenter<V> {
    /**
     * 数据源
     */
    protected DataManager mDataManager;

    protected CompositeDisposable mCompositeDisposable;

    public RxPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 新增订阅
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 常用Flowable build
     */
    protected <T> Flowable<T> buildFlowable(Flowable<T> flowable) {
        return flowable.throttleFirst(Configs.THROTTLE_DELAY, TimeUnit.MILLISECONDS)
                .compose(getView().bindToLife())
                .compose(TransformUtils.defaultScheduler());
    }

    /**
     * 新增订阅
     */
    protected <T> void addSubscribe(Flowable<T> flowable, ResourceSubscriber<T> subscriber) {
        addSubscribe(buildFlowable(flowable).subscribeWith(subscriber));
    }

    /**
     * 常用请求
     */
    protected <T> void performRequest(Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        addSubscribe(buildFlowable(flowable).subscribeWith(subscriber));
    }

    /**
     * 请求 初始化
     */
    protected <T> void performRequestInit(Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        getView().onStatusInit();
        performRequest(flowable, subscriber);
    }

    /**
     * 请求 带进度条
     */
    protected <T> void performRequestLoading(Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        getView().onStatusLoading();
        performRequest(flowable, subscriber);
    }

    /**
     * 初始化加载
     */
    public void initRequestLoading(String... params) {
    }
}
