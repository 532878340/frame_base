package com.frame.manager.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.frame.FrameApplication;
import com.frame.R;
import com.frame.di.AppComponent;
import com.frame.manager.base.contracts.IContracts;
import com.frame.manager.base.presenter.FrameRootPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

public abstract class FrameRootActivity<P extends FrameRootPresenter> extends RxAppCompatActivity implements IContracts.IView {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.container)
    FrameLayout mContainer;

    protected Context mCtx;

    @Inject
    protected P mPresenter;

    /**
     * 字段标识是否含有toolbar
     */
    private static final String ARGS_TOOLBAR = "ARGS_TOOLBAR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_container);

        mCtx = this;

        ButterKnife.bind(this);
        initInjector();

        initView();
    }

    /**
     * 资源布局
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化injector
     */
    protected abstract void initInjector();

    /**
     * 初始化视图
     */
    protected void initView(){
        View.inflate(mCtx,attachLayoutRes(),mContainer);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void finishLoading() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }

    /**
     * 获取 ApplicationComponent
     */
    public AppComponent getAppComponent() {
        return FrameApplication.getAppComponent();
    }
}
