package com.frame.di;

import android.app.Application;

import com.frame.di.annotation.ApplicationScope;
import com.frame.manager.ApiService;

import dagger.Component;

/**
 * Created by Zijin on 2017/7/20.
 * Email:info@zijinqianbao.com
 */
@ApplicationScope
@Component(modules = {AppModule.class,ApiServiceModule.class})
public interface AppComponent {
    Application getApplication();

    ApiService getApiService();
}
