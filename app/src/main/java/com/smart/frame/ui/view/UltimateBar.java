package com.smart.frame.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.smart.frame.R;
import com.smart.frame.utils.ScreenUtils;

/**
 * Description: 透明状态栏工具类
 * Created by Zijin on 2017/8/7.
 * Email: info@zijinqianbao.com
 */

public class UltimateBar {
    private Activity mActivity;

    public UltimateBar(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 设置透明状态栏
     *
     * @param isDraw 是否为侧滑DrawerLayout
     */
    public void setTransparentBar(boolean isDraw) {
        final Window window = mActivity.getWindow();
        final int finalColor = mActivity.getResources().getColor(R.color.statusbar_background);
        final ViewGroup decorView = (ViewGroup) window.getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(isDraw ? Color.TRANSPARENT : finalColor);
            if (isDraw) {
                decorView.addView(createStatusBarView(mActivity, finalColor), 0);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            decorView.addView(createStatusBarView(mActivity, finalColor));
        }
    }

    /**
     * 创建状态栏视图
     */
    private View createStatusBarView(Context context, @ColorInt int color) {
        View mStatusBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusBarHeight(context));
        params.gravity = Gravity.TOP;
        mStatusBarTintView.setLayoutParams(params);
        mStatusBarTintView.setBackgroundColor(color);
        return mStatusBarTintView;
    }
}
