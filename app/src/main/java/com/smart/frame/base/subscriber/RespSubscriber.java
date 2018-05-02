package com.smart.frame.base.subscriber;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.smart.frame.base.bean.Result;
import com.smart.frame.base.contract.IBaseView;

import java.lang.reflect.ParameterizedType;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * rx subscriber
 *
 * @author Gjm
 * @date 2018/1/16
 */
public abstract class RespSubscriber<T> extends ResourceSubscriber<Result> {
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
    private boolean showErrorState;

    public RespSubscriber(IBaseView view) {
        this(view,false);
    }

    public RespSubscriber(IBaseView view, boolean showErrorState) {
        this(view,null,showErrorState);
    }

    public RespSubscriber(IBaseView view, String errorMsg, boolean showErrorState) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.showErrorState = showErrorState;
    }

    @Override
    public void onNext(Result result) {
        if(mView == null){
            return;
        }

        Logger.d(result);

        if(result.isSuccess()){
            mView.onStatusMain();
            onSuccess(new Gson().fromJson(result.getData(),getTClass()));
        }else if(showErrorState){
            mView.onStatusError();
        }else{
            mView.showMessage(result.getMsg());
            mView.onStatusMain();
            onIllegal(result);
        }
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
        }else{
            mView.onStatusMain();
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T resp);

    /**
     * 子类有需要可单独处理
     */
    protected void onIllegal(Result result){
    }

    public IBaseView getBindView(){
        return mView;
    }

    /**
     * 获取泛型类型
     * @return
     */
    public Class<T> getTClass(){
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
