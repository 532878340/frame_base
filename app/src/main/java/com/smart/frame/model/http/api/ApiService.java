package com.smart.frame.model.http.api;


import com.smart.frame.base.bean.Repo;
import com.smart.frame.manager.constants.Configs;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Description: api接口
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public interface ApiService {
    String BASE_URL = Configs.BASE_URL;

    @GET(ApiServiceRoutes.INDEX)
    Observable<Repo> platformIndex();
}
