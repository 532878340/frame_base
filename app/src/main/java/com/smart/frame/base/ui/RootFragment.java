package com.smart.frame.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.smart.frame.R;
import com.smart.frame.base.Status;
import com.smart.frame.base.presenter.BasePresenter;
import com.smart.frame.ui.view.basic.loading.LoadingView;

import butterknife.BindView;

/**
 * fragment基类，处理不同状态的view
 * @author Gjm
 * @date 2018/01/12
 */
public abstract class RootFragment<P extends BasePresenter> extends BaseFragment<P>{
    @BindView(R.id.loading)
    protected LoadingView mLoadingView;

    /**
     * 记录当前状态
     */
    protected Status mStatus = Status.SUCCESS;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(initLoadingUI()){
            initRequestLoading();
            mLoadingView.setOnRetryListener(() -> initRequestLoading());
        }
    }

    @Override
    public void onStatusMain() {
        if(mStatus != Status.SUCCESS){
            mStatus = Status.SUCCESS;
            mLoadingView.success();
        }
    }

    @Override
    public void onStatusLoading() {
        if(mStatus != Status.LOADING){
            mStatus = Status.LOADING;
            mLoadingView.loading();
        }
    }

    @Override
    public void onStatusError() {
        if(mStatus != Status.FAIL){
            mStatus = Status.FAIL;
            mLoadingView.fail();
        }
    }

    /**
     * 配置初始化页面是否显示Loading页面
     */
    protected boolean initLoadingUI(){
        return false;
    }

    /**
     * 初始化请求操作
     * {@link #initLoadingUI()}
     */
    protected void initRequestLoading(){
    }
}
