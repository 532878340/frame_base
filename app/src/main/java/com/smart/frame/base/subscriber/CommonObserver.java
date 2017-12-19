package com.smart.frame.base.subscriber;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.smart.frame.base.contract.IBaseView;

import io.reactivex.observers.DefaultObserver;
import retrofit2.HttpException;

/**
 * Description: rx subscriber
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class CommonObserver<T> extends DefaultObserver<T> {
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

    public CommonObserver(IBaseView view) {
        this(view,true);
    }

    public CommonObserver(IBaseView view, boolean showErrorState) {
        this(view,null,showErrorState);
    }

    public CommonObserver(IBaseView view, String errorMsg, boolean showErrorState) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.showErrorState = showErrorState;
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
