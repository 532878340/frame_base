package com.demo.tmp;

import com.frame.di.AppComponent;
import com.frame.di.annotation.ActivityScope;
import com.frame.di.component.IFrameComponent;

import dagger.Component;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

@ActivityScope
@Component(modules = TmpModule.class, dependencies = AppComponent.class)
public interface TmpComponent extends IFrameComponent<TmpActivity> {
}
