package com.frame.manager.base.callback;


import com.frame.data.entity.Repo;
import com.frame.manager.base.RequestFlag;

/**
 * Created by Zijin on 2017/7/24.
 * Email:info@zijinqianbao.com
 * 默认callback实现
 */

public abstract class DefaultCallBack<T> implements CallBack<T> {
    @Override
    public void onRequestIllegal(Repo<T> repo, RequestFlag flag) {
    }

    @Override
    public void onRequestFailed(RequestFlag flag) {
    }
}
