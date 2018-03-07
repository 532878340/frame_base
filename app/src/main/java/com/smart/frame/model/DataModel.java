package com.smart.frame.model;

import android.support.annotation.NonNull;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.utils.TransformUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Model层
 *
 * @author Gjm
 * @date 2018/3/7
 */
public class DataModel {
    @Inject
    public DataModel(){
    }

    /**
     * 通用网络请求
     * @param flowable 请求flowable
     * @param subscriber 响应
     * @param <T> 响应类型
     */
    public <T> void performRequest(@NonNull Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        flowable.throttleFirst(Configs.THROTTLE_DELAY, TimeUnit.MILLISECONDS)
                .compose(subscriber.getBindView().bindToLife())
                .compose(TransformUtils.flowableIOToMain())
                .subscribe(subscriber);
    }
}
