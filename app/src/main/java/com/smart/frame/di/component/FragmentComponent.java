package com.smart.frame.di.component;

import android.support.v4.app.Fragment;

import com.smart.frame.di.annotation.scope.FragmentScope;
import com.smart.frame.di.module.FragmentModule;
import com.smart.frame.ui.fetures.fragment.IndexFragment;

import dagger.Component;

/**
 * Description: fragment component
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment getFragment();

    void inject(IndexFragment indexFragment);
}
