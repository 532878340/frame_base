package com.smart.frame.di.module;

import android.support.v4.app.Fragment;

import com.smart.frame.di.annotation.scope.FragmentScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * Description: fragment module
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @FragmentScope
    RxPermissions provideRxPermissions(){
        return new RxPermissions(mFragment.getActivity());
    }
}
