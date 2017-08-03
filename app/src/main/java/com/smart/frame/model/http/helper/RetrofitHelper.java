package com.smart.frame.model.http.helper;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.model.http.api.ApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Description: retrofit helper
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

@ApplicationScope
public class RetrofitHelper implements HttpHelper {
    private ApiService mApiService;

    @Inject
    public RetrofitHelper(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<Repo> platformIndex() {
        return mApiService.platformIndex();
    }
}
