package com.func.di.component;

import com.frame.di.AppComponent;
import com.frame.di.annotation.ActivityScope;
import com.frame.di.component.IFrameComponent;
import com.func.di.module.BaseListModule;
import com.func.ui.activity.ListActivity;

import dagger.Component;

/**
 * Description:
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = BaseListModule.class)
public interface RecycleComponent extends IFrameComponent<ListActivity>{
}
