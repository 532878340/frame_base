package com.smart.frame.di.component;

import com.smart.frame.app.App;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.di.module.AppModule;
import com.smart.frame.di.module.HttpModule;
import com.smart.frame.model.DataManager;

import dagger.Component;

/**
 * Description: 全局AppComponent
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

@ApplicationScope
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    /**
     * 提供App的context
     */
    App getAppContext();

    /**
     * 提供数据
     */
    DataManager getDataManager();
}
