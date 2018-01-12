package com.smart.frame.base.contract;

/**
 * mvp base presenter
 * @author Gjm
 * @date 2018/01/12
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
