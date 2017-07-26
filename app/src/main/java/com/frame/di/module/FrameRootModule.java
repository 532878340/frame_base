package com.frame.di.module;

import com.frame.manager.base.contracts.IContracts;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

@Module
public abstract class FrameRootModule<V extends IContracts.IView> {
    private V mView;

    public FrameRootModule(V v) {
        this.mView = v;
    }

    @Provides
    public V getView() {
        return mView;
    }
}
