package com.smart.frame.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;

import com.smart.frame.app.App;
import com.smart.frame.base.contract.IMVPContract;
import com.smart.frame.base.presenter.BasePresenter;
import com.smart.frame.di.component.DaggerFragmentComponent;
import com.smart.frame.di.component.FragmentComponent;
import com.smart.frame.di.module.FragmentModule;
import com.smart.frame.utils.SnackBarUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Description: MVP fragment基类
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public abstract class BaseFragment<P extends BasePresenter> extends SimpleFragment implements IMVPContract.IBaseView{
    @Inject
    protected P mPresenter;

    /**
     * 获取activity Component
     */
    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    /**
     * 获取activity module
     */
    @NonNull
    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if(mPresenter != null){
            mPresenter.attach(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter != null){
            mPresenter.detach();
        }
        super.onDestroyView();
    }

    /**
     * 注入
     */
    protected void initInject(){
    }

    @Override
    public void onStatusLoading() {
    }

    @Override
    public void onStatusMain() {
    }

    @Override
    public void onStatusError() {
    }

    @Override
    public void showMessage(String message) {
        SnackBarUtils.showSnackBar(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0),message);
    }

    @Override
    public void useNightMode(boolean isNight) {
        AppCompatDelegate.setDefaultNightMode(isNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }
}
