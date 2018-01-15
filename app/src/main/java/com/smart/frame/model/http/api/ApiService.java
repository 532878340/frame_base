package com.smart.frame.model.http.api;


import com.smart.frame.base.bean.Repo;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * api接口
 * @author Gjm
 * @date 2018/01/12
 */


public interface ApiService {
    @GET(ApiServiceRoutes.INDEX)
    Observable<Repo<String>> platformIndex();

    @POST(ApiServiceRoutes.LOGIN)
    Flowable<Repo<LoginResp>> login(@Query("loginName") String loginName,@Query("password") String password);
}
