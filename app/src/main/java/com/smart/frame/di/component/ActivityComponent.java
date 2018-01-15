package com.smart.frame.di.component;


import android.app.Activity;

import com.smart.frame.di.annotation.scope.ActivityScope;
import com.smart.frame.di.module.ActivityModule;
import com.smart.frame.ui.fetures.MainActvitiy;

import dagger.Component;

/**
 * activity component
 * @author Gjm
 * @date 2018/01/12
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActvitiy activity);
}
