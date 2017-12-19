package com.smart.frame.base.contract;

/**
 * Description: mvp base presenter
 * Created by Zijin on 2017/12/19.
 * Email: info@zijinqianbao.com
 */

public interface IBasePresenter<V> extends IContract.IPresenter {
    /**
     * attach
     */
    void attach(V view);

    /**
     * detach
     */
    void detach();
}
