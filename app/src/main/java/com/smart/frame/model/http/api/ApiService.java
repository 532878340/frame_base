package com.smart.frame.model.http.api;


import com.smart.frame.base.bean.Repo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Description: api
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public interface ApiService {
    String BASE_URL = "http://106.14.112.233/app/";

    @GET(ApiServiceRoutes.INDEX)
    Observable<Repo> platformIndex();
}
