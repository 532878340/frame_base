package com.frame.manager.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.frame.R;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.contracts.IContracts;
import com.frame.manager.base.presenter.FrameRootPresenter;
import com.frame.manager.utils.SnackBarUtil;
import com.frame.ui.view.frame.ScrollChildSwipeRefreshLayout;
import com.frame.ui.view.frame.ToolBarHelperView;
import com.frame.ui.view.frame.loading.LoadingView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Zijin on 2017/7/25.
 * Email:info@zijinqianbao.com
 */

public abstract class FrameRootFragment<P extends FrameRootPresenter> extends RxFragment implements IContracts.IView {
    @BindView(R.id.container)
    protected FrameLayout mContainer;
    @BindView(R.id.refreshLayout)
    protected ScrollChildSwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.loadingView)
    protected LoadingView mLoadingView;
    @BindView(R.id.toolBar)
    protected ToolBarHelperView mToolBar;

    private Unbinder unbinder;
    private View mRootView;

    @Inject
    protected P mPresenter;

    protected Context mCtx;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.base_container, container, false);

            mContainer = mRootView.findViewById(R.id.container);

            if (attachLayoutRes() != 0) {
                View.inflate(mCtx, attachLayoutRes(), mContainer);
            }
        }

        unbinder = ButterKnife.bind(this, mRootView);
        initInjector();
        initView();
        initData(RequestFlag.FLAG_INIT);

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
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
        mRefreshLayout.setOnRefreshListener(() -> initData(RequestFlag.FLAG_REFRESH));
        mLoadingView.setOnRetryListener(() -> initData(RequestFlag.FLAG_DIALOG));
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
        ((AppCompatActivity) mCtx).setSupportActionBar(mToolBar);
        ((AppCompatActivity) mCtx).getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
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
    public void showNetError() {
        mLoadingView.fail();
    }

    @Override
    public void showMessage(String message) {
        SnackBarUtil.showSnackBar(mLoadingView, message);
    }

    @Override
    public void finishLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
