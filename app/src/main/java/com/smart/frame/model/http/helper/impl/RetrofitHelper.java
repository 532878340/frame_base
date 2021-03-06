package com.smart.frame.model.http.helper.impl;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.bean.Result;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.model.http.api.ApiService;
import com.smart.frame.model.http.helper.HttpHelper;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.bean.resp.PatchInfo;
import com.smart.frame.ui.fetures.user.bean.resp.RegisterResp;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * retrofit helper
 *
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
    public Flowable<Repo<LoginResp>> login(Map<String,String> param) {
        return mApiService.login(param);
    }

    @Override
    public Flowable<Repo<RegisterResp>> register(Map<String, String> param) {
        return mApiService.register(param);
    }

    @Override
    public Flowable<Repo<Object>> sendSms(Map<String, String> param) {
        return mApiService.sendSms(param);
    }

    @Override
    public Flowable<Repo<Object>> findPwdVerify(Map<String, String> param) {
        return mApiService.findPwdVerify(param);
    }

    @Override
    public Flowable<Repo<Object>> resetLoginPwd(Map<String, String> param) {
        return mApiService.resetLoginPwd(param);
    }

    @Override
    public Flowable<Repo<ArrayList<PatchInfo>>> getPatchInfo(Map<String, String> param) {
        return mApiService.getPatchInfo(param);
    }

    @Override
    public Flowable<Repo<Object>> register1(RequestBody str) {
        return mApiService.register1(str);
    }

    @Override
    public Flowable<Result> performReq(RequestBody body) {
        return mApiService.performReq(body);
    }
}
