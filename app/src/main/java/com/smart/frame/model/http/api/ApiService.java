package com.smart.frame.model.http.api;


import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.bean.Result;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.bean.resp.PatchInfo;
import com.smart.frame.ui.fetures.user.bean.resp.RegisterResp;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * api接口
 *
 * @author Gjm
 * @date 2018/01/12
 */


public interface ApiService {
    @GET(ApiServiceRoutes.INDEX)
    Observable<Repo<String>> platformIndex();

    @FormUrlEncoded
    @POST(ApiServiceRoutes.LOGIN)
    Flowable<Repo<LoginResp>> login(@FieldMap Map<String,String> param);

    @FormUrlEncoded
    @POST(ApiServiceRoutes.REGISTER)
    Flowable<Repo<RegisterResp>> register(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiServiceRoutes.SENDSMS)
    Flowable<Repo<Object>> sendSms(@FieldMap Map<String,String> param);

    @GET(ApiServiceRoutes.FIND_PWD_VERIFY)
    Flowable<Repo<Object>> findPwdVerify(@QueryMap Map<String,String> param);

    @FormUrlEncoded
    @POST(ApiServiceRoutes.RESET_LOGIN_PWD)
    Flowable<Repo<Object>> resetLoginPwd(@FieldMap Map<String,String> param);

    @GET(ApiServiceRoutes.GET_PATCH_INFO)
    Flowable<Repo<ArrayList<PatchInfo>>> getPatchInfo(@QueryMap Map<String,String> param);

    @Headers({
            "h-scx-val:fdacf80828bf4e68",
            "Content-Type:application/json"
    })
    @POST("v1/api/send")
    Flowable<Repo<Object>> register1(@Body RequestBody str);

    @Headers({
            "h-scx-val:fdacf80828bf4e68",
            "Content-Type:application/json"
    })
    @POST("v1/api/send")
    Flowable<Result> performReq(@Body RequestBody str);
}
