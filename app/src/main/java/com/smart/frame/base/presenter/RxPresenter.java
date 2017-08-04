package com.smart.frame.base.presenter;

import com.smart.frame.base.contract.IMVPContract;
import com.smart.frame.manager.constants.Constants;
import com.smart.frame.model.DataManager;
import com.smart.frame.utils.TransformUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class RxPresenter<V extends IMVPContract.IBaseView> extends BasePresenter<V>{
    /**
     * 数据源
     */
    protected DataManager mDataManager;

    public RxPresenter(DataManager dataManager){
        this.mDataManager = dataManager;
    }

    /**
     * 常用Observable build
     */
    protected <T> Observable<T> wrapObservable(Observable<T> observable){
        return observable.throttleFirst(Constants.THROTTLE_DELAY,TimeUnit.MILLISECONDS)
                .compose(getView().bindToLife())
                .compose(TransformUtils.defaultScheduler());
    }
}
