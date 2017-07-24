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
    void onRequestSuccess(Repo<T> repo,RequestFlag flag);

    /**
     * 请求非法 Repo.isOk() false
     */
    void onRequestIllegal(Repo<T> repo,RequestFlag flag);

    /**
     * 请求异常
     */
    void onRequestFailed(RequestFlag flag);
}
