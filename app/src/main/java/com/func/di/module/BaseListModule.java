package com.func.di.module;

import com.frame.di.module.FrameRootModule;
import com.frame.manager.base.contracts.IListContract;

import dagger.Module;

/**
 * Description:
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

@Module
public class BaseListModule extends FrameRootModule<IListContract.IListView>{
    public BaseListModule(IListContract.IListView iListView) {
        super(iListView);
    }
}
