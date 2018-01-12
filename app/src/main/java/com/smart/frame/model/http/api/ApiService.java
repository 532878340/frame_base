package com.smart.frame.model.http.api;


import com.smart.frame.base.bean.Repo;
import com.smart.frame.manager.constants.Configs;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * api接口
 * @author Gjm
 * @date 2018/01/12
 */


public interface ApiService {
    String BASE_URL = Configs.BASE_URL;

    @GET(ApiServiceRoutes.INDEX)
    Observable<Repo<String>> platformIndex();
}
