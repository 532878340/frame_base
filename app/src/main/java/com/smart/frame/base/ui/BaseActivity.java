package com.smart.frame.base.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.smart.frame.app.App;
import com.smart.frame.base.Status;
import com.smart.frame.base.contract.IMVPContract;
import com.smart.frame.base.presenter.BasePresenter;
import com.smart.frame.di.component.ActivityComponent;
import com.smart.frame.di.component.DaggerActivityComponent;
import com.smart.frame.di.module.ActivityModule;
import com.smart.frame.utils.SnackBarUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Description: MVP activity基类
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */
public abstract class BaseActivity<P extends BasePresenter> extends SimpleActivity implements IMVPContract.IBaseView{
    @Inject
    protected P mPresenter;

    /**
     * 获取activity Component
     */
    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    /**
     * 获取activity module
     */
    @NonNull
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    @CallSuper
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if(mPresenter != null){
            mPresenter.attach(this);
        }
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.detach();
        }
        super.onDestroy();
    }

    @Override
    public void useNightMode(boolean isNight) {
        AppCompatDelegate.setDefaultNightMode(isNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }

    /**
     * 注入
     */
    protected void initInject(){
//        getActivityComponent().inject(this);
    }

    @Override
    public void setStatus(Status status) {

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
        SnackBarUtils.showSnackBar(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0),message);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }
}