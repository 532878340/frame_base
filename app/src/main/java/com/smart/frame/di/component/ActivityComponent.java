package com.smart.frame.di.component;


import android.app.Activity;

import com.smart.frame.di.annotation.scope.ActivityScope;
import com.smart.frame.di.module.ActivityModule;
import com.smart.frame.ui.fetures.activity.MainActvitiy;

import dagger.Component;

/**
 * Description: activity component
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActvitiy activity);
}
