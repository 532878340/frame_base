package com.smart.frame.base.contract;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public interface IContract {
    interface IPresenter<V>{
        /**
         * attach
         */
        void attach(V view);

        /**
         * detach
         */
        void detach();
    }

    interface IView{
    }
}