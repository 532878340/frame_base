package com.smart.frame.di.module;

import com.smart.frame.app.App;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.model.DataManager;
import com.smart.frame.model.db.DBHelper;
import com.smart.frame.model.db.GreenDaoHelper;
import com.smart.frame.model.http.helper.HttpHelper;
import com.smart.frame.model.http.helper.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Description:全局AppModule
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

@Module
public class AppModule {
    private App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @ApplicationScope
    App provideApp(){
        return mApp;
    }

    @ApplicationScope
    @Provides
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @ApplicationScope
    @Provides
    DBHelper provideDBHelper(GreenDaoHelper greenDaoHelper){
        return greenDaoHelper;
    }

    @ApplicationScope
    @Provides
    DataManager provideDataManager(HttpHelper httpHelper,DBHelper dbHelper){
        return new DataManager(httpHelper,dbHelper);
    }
}
