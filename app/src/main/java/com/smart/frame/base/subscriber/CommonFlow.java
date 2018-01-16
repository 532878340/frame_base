package com.smart.frame.base.subscriber;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.smart.frame.base.contract.IBaseView;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import retrofit2.HttpException;

/**
 * rx subscriber
 *
 * @author Gjm
 * @date 2018/1/16
 */
public class CommonFlow<T> implements FlowableSubscriber<T> {
    /**
     * view
     */
    protected IBaseView mView;
    /**
     * 错误提示
     */
    private String mErrorMsg;
    /**
     * 是否显示错误布局
     */
    protected boolean showErrorState;

    public CommonFlow(IBaseView view) {
        this(view,true);
    }

    public CommonFlow(IBaseView view, boolean showErrorState) {
        this(view,null,showErrorState);
    }

    public CommonFlow(IBaseView view, String errorMsg, boolean showErrorState) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.showErrorState = showErrorState;
    }

    @Override
    public void onSubscribe(Subscription s) {
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        if(mView == null){
            return;
        }

        if (!TextUtils.isEmpty(mErrorMsg)) {
            mView.showMessage(mErrorMsg);
        } else if (e instanceof HttpException) {
            mView.showMessage("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mView.showMessage("未知错误ヽ(≧Д≦)ノ");
            Logger.d(e.toString());
        }
        if (showErrorState) {
            mView.onStatusError();
        }
    }

    @Override
    public void onComplete() {

    }
}
