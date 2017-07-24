package com.frame.manager.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.frame.FrameApplication;
import com.frame.R;
import com.frame.di.AppComponent;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.contracts.IContracts;
import com.frame.manager.base.presenter.FrameRootPresenter;
import com.frame.manager.utils.SnackBarUtil;
import com.frame.ui.view.ToolBarHelperView;
import com.frame.ui.view.frame.loading.LoadingView;
import com.frame.ui.view.frame.loading.OnRetryListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

public abstract class FrameRootActivity<P extends FrameRootPresenter> extends RxAppCompatActivity implements IContracts.IView, SwipeRefreshLayout.OnRefreshListener, OnRetryListener {
    @BindView(R.id.toolBar)
    protected ToolBarHelperView mToolBar;
    @BindView(R.id.container)
    protected FrameLayout mContainer;
    @BindView(R.id.loadingView)
    protected LoadingView mLoadingView;
    @BindView(R.id.refreshLayout)
    protected SwipeRefreshLayout mSwipeRefresh;

    protected Context mCtx;

    @Inject
    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_container);

        mCtx = this;

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
    protected abstract void initInjector();

    /**
     * 初始化数据
     */
    protected abstract void initData(RequestFlag flag);

    /**
     * 初始化视图
     */
    @CallSuper
    protected void initView() {
        View.inflate(mCtx, attachLayoutRes(), mContainer);
        mSwipeRefresh.setOnRefreshListener(this);
        mLoadingView.setOnRetryListener(this);
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
    public void onRefresh() {
        initData(RequestFlag.FLAG_REFRESH);
    }

    @Override
    public void onRetry() {
        initData(RequestFlag.FLAG_DIALOG);
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
        mLoadingView.postDelayed(() -> mLoadingView.success(), 4000);
    }

    @Override
    public void showMessage(String message) {
        SnackBarUtil.showSnackBar(mContainer, message);
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
