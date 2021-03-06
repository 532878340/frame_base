package com.smart.frame.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import com.smart.frame.R;
import com.smart.frame.manager.ActivityContainer;
import com.smart.frame.ui.view.basic.TitleBar;
import com.smart.frame.ui.view.basic.ToolBarHelperView;
import com.smart.frame.utils.ActivityUtils;
import com.smart.frame.utils.DensityHelper;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 顶层无MVP activity
 *
 * @author Gjm
 * @date 2018/01/12
 */
public abstract class SimpleActivity extends RxAppCompatActivity {
    protected Context mCtx;

    @BindView(R.id.toolBar)
    protected ToolBarHelperView mToolBar;
    @BindView(R.id.titleBar)
    protected TitleBar mTitleBar;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityHelper.getInstance().active(this);
        setContentView(R.layout.base_container);

        ActivityContainer.getInstance().add(this);

        mCtx = this;
        if (getLayoutRes() != 0) {
            View.inflate(mCtx, getLayoutRes(), findViewById(R.id.container));
        }

        mUnbinder = ButterKnife.bind(this);

        onViewCreated();
        initViewOrData();
    }

    /**
     * 资源布局
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * 操作视图
     */
    protected abstract void initViewOrData();

    /**
     * view创建完成
     */
    protected void onViewCreated() {
    }

    protected final void initToolBar(boolean homeAsUpEnabled, @StringRes int titleRes) {
        initToolBar(homeAsUpEnabled, getString(titleRes));
    }

    /**
     * 初始化 ToolBar
     */
    protected final void initToolBar(boolean homeAsUpEnabled, String title) {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        mToolBar.setTitle(title);
    }

    @Override
    protected void onDestroy() {
        ActivityUtils.fixInputMethodManagerLeak(this);
        ActivityContainer.getInstance().remove(this);

        mUnbinder.unbind();
        super.onDestroy();
    }

    /**
     * 获取参数
     */
    protected String getBundleValue(String key){
        return getIntent().getExtras() == null ? null : getIntent().getExtras().getString(key);
    }
}
