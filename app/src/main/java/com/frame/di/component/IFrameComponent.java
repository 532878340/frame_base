package com.frame.di.component;

import com.frame.manager.base.contracts.IContracts;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

public interface IFrameComponent<V extends IContracts.IView> {
    void inject(V v);
}
