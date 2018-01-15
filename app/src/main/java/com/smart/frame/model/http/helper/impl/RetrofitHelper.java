package com.smart.frame.model.http.helper.impl;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.model.http.api.ApiService;
import com.smart.frame.model.http.helper.HttpHelper;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * retrofit helper
 * @author Gjm
 * @date 2018/01/12
 */

@ApplicationScope
public class RetrofitHelper implements HttpHelper {
    private ApiService mApiService;

    @Inject
    public RetrofitHelper(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<Repo<String>> platformIndex() {
        return mApiService.platformIndex();
    }

    @Override
    public Flowable<Repo<LoginResp>> login(String loginName, String password) {
        return mApiService.login(loginName,password);
    }
}
