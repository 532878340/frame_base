package com.smart.frame.di.module;

import android.app.Activity;

import com.smart.frame.di.annotation.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * Description: activity module
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

@Module
public class ActivityModule{
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @ActivityScope
    RxPermissions provideRxPermissions(){
        return new RxPermissions(mActivity);
    }
}
