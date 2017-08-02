package com.frame.manager.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.FrameLayout;

import com.frame.FrameApplication;
import com.frame.R;
import com.frame.di.AppComponent;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.contracts.IContracts;
import com.frame.manager.base.presenter.FrameRootPresenter;
import com.frame.manager.utils.SnackBarUtils;
import com.frame.view.ScrollChildSwipeRefreshLayout;
import com.frame.view.ToolBarHelperView;
import com.frame.view.loading.LoadingView;
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
    protected FrameLayout mContainer;

    @BindView(R.id.toolBar)
    protected ToolBarHelperView mToolBar;
    @BindView(R.id.loadingView)
    protected LoadingView mLoadingView;
    @BindView(R.id.refreshLayout)
    protected ScrollChildSwipeRefreshLayout mSwipeRefresh;

    protected Context mCtx;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_container);

        mCtx = this;

        mContainer = findViewById(R.id.container);

        if (attachLayoutRes() != 0) {
            View.inflate(mCtx, attachLayoutRes(), mContainer);
        }

        ButterKnife.bind(this);
        initInjector();

        initView();
        initData(RequestFlag.FLAG_INIT);
    }

    /**
     * 资源布局
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化injector
     */
    protected void initInjector() {
    }

    /**
     * 初始化数据
     */
    protected void initData(RequestFlag flag) {
    }

    /**
     * 初始化视图
     */
    @CallSuper
    protected void initView() {
        mSwipeRefresh.setEnabled(enableRefresh());
        mSwipeRefresh.setOnRefreshListener(() -> initData(RequestFlag.FLAG_REFRESH));
        mLoadingView.setOnRetryListener(() -> initData(RequestFlag.FLAG_DIALOG));
    }

    /**
     * 设置下拉刷新是否有效
     */
    protected boolean enableRefresh() {
        return false;
    }

    protected void initToolBar(boolean homeAsUpEnabled, @StringRes int titleRes) {
        initToolBar(homeAsUpEnabled, getString(titleRes));
    }

    /**
     * 初始化 ToolBar
     */
    protected void initToolBar(boolean homeAsUpEnabled, String title) {
        mToolBar.setVisibility(View.VISIBLE);
        mToolBar.setTitle(title);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    public void initLoading() {
        mLoadingView.init();
    }

    @Override
    public void showLoading() {
        mLoadingView.loading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.success();
    }

    @Override
    public void showMessage(String message) {
        SnackBarUtils.showSnackBar(mContainer, message);
    }

    @Override
    public void showNetError() {
        mLoadingView.fail();
    }

    @Override
    public void finishLoading() {
        mSwipeRefresh.setRefreshing(false);
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
