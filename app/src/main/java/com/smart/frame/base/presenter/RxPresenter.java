package com.smart.frame.base.presenter;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.bean.Result;
import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.base.subscriber.RespSubscriber;
import com.smart.frame.model.DataManager;
import com.smart.frame.model.DataModel;

import javax.inject.Inject;

import io.reactivex.Flowable;

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

    @Inject
    protected DataModel mDataModel;

    public RxPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 常用请求
     */
    protected <T> void performRequest(Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        mDataModel.performRequest(flowable,subscriber);
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
