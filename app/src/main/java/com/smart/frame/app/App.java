package com.smart.frame.app;

import android.app.Application;

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
    private static App mInstance;
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        InitializeService.init(this);
    }

    public static App getInstance(){
        return mInstance;
    }

    /**
     * 获取全局AppComponent
     * @return
     */
    public static AppComponent getAppComponent(){
        if(mAppComponent == null){
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return mAppComponent;
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
