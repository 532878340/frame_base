package com.frame.manager.base.callback;

import com.frame.data.entity.Repo;
import com.frame.manager.utils.Logger;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by Zijin on 2017/7/25.
 * Email:info@zijinqianbao.com
 */

public abstract class HttpResultObserver<T> extends DefaultObserver<Repo<T>> implements CallBack<T> {
    private static final String TAG = "HttpResultObserver";

    @Override
    public void onNext(Repo<T> repo) {
        Logger.d(TAG, "onNext: " + repo);

        if (repo.isOk()) {
            onSuccess(repo);
        } else {
            onIllegal(repo);
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.d(TAG, "onError: " + e.getMessage());

        onFailed();
    }

    @Override
    public void onComplete() {
    }
}
