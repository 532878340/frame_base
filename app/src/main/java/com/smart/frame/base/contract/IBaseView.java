package com.smart.frame.base.contract;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * mvp base view
 * @author Gjm
 * @date 2018/01/12
 */
public interface IBaseView extends IContract.IView{
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
     * 显示信息
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
