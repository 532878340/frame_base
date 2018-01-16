package com.smart.frame.base.presenter;

import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.DataManager;
import com.smart.frame.utils.TransformUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 处理rx相关的presenter
 * @author Gjm
 * @date 2018/01/12
 */
public class RxPresenter<V extends IBaseView> extends BasePresenter<V>{
    /**
     * 数据源
     */
    protected DataManager mDataManager;

    public RxPresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }

    /**
     * 常用Flowable build
     * @param flowable
     * @param <T>
     * @return
     */
    protected <T> Flowable<T> wrapFlowable(Flowable<T> flowable){
        return flowable.throttleFirst(Configs.THROTTLE_DELAY,TimeUnit.MILLISECONDS)
                .compose(getView().bindToLife())
                .compose(TransformUtils.defaultScheduler());
    }
}
