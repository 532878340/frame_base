package com.smart.frame.app;

import android.app.Application;

import com.smart.frame.di.component.AppComponent;
import com.smart.frame.manager.ActivityContainer;

/**
 * Application对象
 *
 * @author Gjm
 * @date 2018/01/12
 */

public class App{
    public static synchronized Application getInstance() {
        return AppLike.getInstance();
    }

    /**
     * 获取全局AppComponent
     */
    public static AppComponent getAppComponent() {
        return AppLike.getAppComponent();
    }

    /**
     * 退出应用
     */
    public void exit() {
        ActivityContainer.getInstance().finishAll();
    }
}
