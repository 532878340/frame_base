package com.smart.frame.base.subscriber;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.contract.IBaseView;

/**
 * 通用请求处理
 * @author Gjm
 * @date 2018/01/12
 */
public abstract class ResultFlowable<T> extends CommonSubscriber<Repo<T>> {
    public ResultFlowable(IBaseView view) {
        super(view);
    }

    public ResultFlowable(IBaseView view, boolean showErrorState) {
        super(view, showErrorState);
    }
}
