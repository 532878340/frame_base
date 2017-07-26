package com.frame.manager.base.callback;


import com.frame.data.entity.Repo;

/**
 * Created by Zijin on 2017/7/24.
 * Email:info@zijinqianbao.com
 * 默认callback实现
 */

public abstract class DefaultCallBack<T> implements CallBack<T> {
    @Override
    public void onIllegal(Repo<T> repo) {
    }

    @Override
    public void onFailed() {
    }
}
