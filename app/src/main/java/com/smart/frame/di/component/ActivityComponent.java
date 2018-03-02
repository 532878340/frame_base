package com.smart.frame.di.component;


import android.app.Activity;

import com.smart.frame.di.annotation.scope.ActivityScope;
import com.smart.frame.di.module.ActivityModule;
import com.smart.frame.ui.fetures.MainActvitiy;
import com.smart.frame.ui.fetures.user.activity.LoginActivity;
import com.smart.frame.ui.fetures.user.activity.RegisterActivity;

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

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);
}
