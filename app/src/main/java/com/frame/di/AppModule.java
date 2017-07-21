package com.frame.di;

import android.app.Application;

import com.frame.di.annotation.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zijin on 2017/7/20.
 * Email:info@zijinqianbao.com
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @ApplicationScope
    @Provides
    public Application getApplication(){
        return mApplication;
    }
}
