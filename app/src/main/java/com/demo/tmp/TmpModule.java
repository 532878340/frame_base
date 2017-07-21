package com.demo.tmp;

import com.frame.di.module.FrameRootModule;

import dagger.Module;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

@Module
public class TmpModule extends FrameRootModule<ITmpContract.ITmpView> {
    public TmpModule(ITmpContract.ITmpView iTmpView) {
        super(iTmpView);
    }
}
