package com.smart.frame.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.FrameLayout;

import com.smart.frame.R;
import com.smart.frame.manager.ActivityContainer;
import com.smart.frame.ui.view.basic.ToolBarHelperView;
import com.smart.frame.ui.view.basic.UltimateBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 顶层无MVP activity
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public abstract class SimpleActivity extends RxAppCompatActivity {
    protected Context mCtx;
    private FrameLayout mContainer;

    @BindView(R.id.toolBar)
    protected ToolBarHelperView mToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_container);

        ActivityContainer.getInstance().add(this);

        mCtx = this;
        mContainer = findViewById(R.id.container);

        if (getLayoutRes() != 0) {
            View.inflate(mCtx, getLayoutRes(), mContainer);
        }

        ButterKnife.bind(this);

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
        mToolBar.setVisibility(View.VISIBLE);
        mToolBar.setTitle(title);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    protected void onDestroy() {
        ActivityContainer.getInstance().remove(this);
        super.onDestroy();
    }

    /**
     * 重写此方法以修改状态栏
     * @param isDrawer 是否为侧滑DrawerLayout
     */
    protected final void enableTranslucentStatus(boolean isDrawer) {
        new UltimateBar(this).setTransparentBar(isDrawer);
    }
}
