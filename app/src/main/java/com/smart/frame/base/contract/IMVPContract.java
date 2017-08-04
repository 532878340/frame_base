package com.smart.frame.base.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Description: MVP contract
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public interface IMVPContract {
    interface IBaseView extends IContract.IView{
        /**
         * 加载中
         */
        void onStatusLoading();

        /**
         * 显示主页
         */
        void onStatusMain();

        /**
         * 显示加载错误页
         */
        void onStatusError();

        /**
         * 显示错误信息
         */
        void showMessage(String message);

        /**
         * 是否使用夜间模式
         */
        void useNightMode(boolean isNight);

        /**
         * 绑定生命周期
         */
        <T> LifecycleTransformer<T> bindToLife();
    }

    interface IBasePresenter<V> extends IContract.IPresenter{
        /**
         * attach
         */
        void attach(V view);

        /**
         * detach
         */
        void detach();
    }
}
