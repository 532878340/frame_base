package com.smart.frame.utils;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调度工具类
 * @author Zijin
 * @date 2017/7/25
 */

public class TransformUtils {
    /**
     * 常用请求，线程调度
     */
    public static <T> FlowableTransformer<T,T> defaultScheduler(){
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 全部 Schedulers.io()
     */
    public static <T> FlowableTransformer<T, T> all_io() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}
