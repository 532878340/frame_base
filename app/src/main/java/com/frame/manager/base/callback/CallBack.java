package com.frame.manager.base.callback;

import com.frame.data.entity.Repo;
import com.frame.manager.base.RequestFlag;

/**
 * Created by Zijin on 2017/7/24.
 * Email:info@zijinqianbao.com
 */

public interface CallBack<T> {
    /**
     * 请求成功 Repo.isOk() true
     */
    void onSuccess(Repo<T> repo);

    /**
     * 请求非法 Repo.isOk() false
     */
    void onIllegal(Repo<T> repo);

    /**
     * 请求异常
     */
    void onFailed();
}
