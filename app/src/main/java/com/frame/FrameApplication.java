package com.frame;

import android.app.Application;
import android.content.Context;

import com.frame.di.ApiServiceModule;
import com.frame.di.AppComponent;
import com.frame.di.AppModule;
import com.frame.di.DaggerAppComponent;

/**
 * Created by Zijin on 2017/7/20.
 * Email:info@zijinqianbao.com
 */

public class FrameApplication extends Application {
    private AppComponent mAppComponent;

    private static Context mCtx;

    @Override
    public void onCreate() {
        super.onCreate();

        mCtx = this;

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public static Context getContext() {
        return mCtx;
    }

    public static AppComponent getAppComponent() {
        return ((FrameApplication) mCtx).mAppComponent;
    }
}
