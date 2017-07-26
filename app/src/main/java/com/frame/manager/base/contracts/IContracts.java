package com.frame.manager.base.contracts;

import android.support.annotation.NonNull;

import com.frame.data.entity.Repo;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.callback.CallBack;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;

/**
 * Created by Zijin on 2017/7/20.
 * Email:info@zijinqianbao.com
 */

public interface IContracts {
    interface IView {
        /**
         * 初始化加载
         */
        void initLoading();

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载动画
         */
        void hideLoading();

        /**
         * 显示网络错误
         */
        void showNetError();

        /**
         * 显示toast信息
         */
        void showMessage(String message);

        /**
         * 完成刷新
         */
        void finishLoading();

        /**
         * 绑定生命周期
         */
        <T> LifecycleTransformer<T> bindToLife();
    }

    interface IPresenter {
        /**
         * 网络请求通用处理
         */
        <T> void performRequest(Observable<Repo<T>> observable, final RequestFlag flag, @NonNull CallBack<T> callBack);
    }
}
