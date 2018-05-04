package com.smart.frame.di.component;

import android.app.Application;

import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.di.module.AppModule;
import com.smart.frame.di.module.HttpModule;
import com.smart.frame.model.DataManager;

import dagger.Component;

/**
 * 全局AppComponent
 * @author Gjm
 * @date 2018/01/12
 */

@ApplicationScope
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    /**
     * 提供App的context
     */
    Application getAppContext();

    /**
     * 提供数据
     */
    DataManager getDataManager();
}
