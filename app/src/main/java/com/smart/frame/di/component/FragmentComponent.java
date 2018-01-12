package com.smart.frame.di.component;

import android.support.v4.app.Fragment;

import com.smart.frame.di.annotation.scope.FragmentScope;
import com.smart.frame.di.module.FragmentModule;
import com.smart.frame.ui.fetures.fragment.IndexFragment;

import dagger.Component;

/**
 * fragment component
 * @author Gjm
 * @date 2018/01/12
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    /**
     * 获取fragment
     * @return
     */
    Fragment getFragment();

    /**
     * 注入
     * @param indexFragment
     */
    void inject(IndexFragment indexFragment);
}
