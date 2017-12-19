package com.smart.frame.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.smart.frame.di.component.AppComponent;
import com.smart.frame.di.component.DaggerAppComponent;
import com.smart.frame.di.module.AppModule;
import com.smart.frame.di.module.HttpModule;
import com.smart.frame.manager.ActivityContainer;
import com.smart.frame.utils.imageloader.ImageLoader;

/**
 * Description: Application对象
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class App extends Application{
    private static App sInstance;
    private static AppComponent sAppComponent;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        InitializeService.init(this);
    }

    public static synchronized App getInstance(){
        return sInstance;
    }

    /**
     * 获取全局AppComponent
     */
    public static AppComponent getAppComponent(){
        if(sAppComponent == null){
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return sAppComponent;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.clearAllMemoryCaches();
    }

    /**
     * 退出应用
     */
    public void exit(){
        ActivityContainer.getInstance().finishAll();
    }
}
