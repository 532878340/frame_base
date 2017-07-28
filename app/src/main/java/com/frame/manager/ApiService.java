package com.frame.manager;

import com.frame.data.entity.Repo;
import com.frame.manager.constants.RouteConstants;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Zijin on 2017/7/12.
 * Email:info@zijinqianbao.com
 */

public interface ApiService {
    @GET(RouteConstants.INDEX)
    Observable<Repo> platformIndex();

    @GET(RouteConstants.STEADY_INFO)
    Observable<Repo> getSteadyInfo(@QueryMap Map<String, String> param);
}
