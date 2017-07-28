package com.frame.manager.base.view;

import android.os.Build;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.frame.manager.base.presenter.FrameRootPresenter;

/**
 * Description: 配置基类activity
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public abstract class BaseActivity<P extends FrameRootPresenter> extends FrameRootActivity<P> {
    @Override
    protected void initView() {
        super.initView();

        if (enableTranslucentStatus()) {
            translucentStatus();
        }
    }

    /**
     * 是否启用透明状态栏
     */
    protected boolean enableTranslucentStatus() {
        return true;
    }

    /**
     * 透明状态栏处理
     */
    private void translucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 是否设置Activity的layout为fitsSystemWindows
     * @return
     */
    protected boolean isFitSystemWindows() {
        return enableTranslucentStatus();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (isFitSystemWindows()) {
            // 设置布局为fitsSystemWindows
            ViewGroup content = findViewById(android.R.id.content);
            if (content != null && content.getChildCount() > 0) {
                content.getChildAt(0).setFitsSystemWindows(true);
            }
        }
    }
}
