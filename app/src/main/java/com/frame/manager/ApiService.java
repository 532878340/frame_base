package com.frame.manager;

import com.demo.tmp.Simple;
import com.frame.data.entity.Repo;
import com.frame.manager.constants.RouteConstants;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Zijin on 2017/7/12.
 */

public interface ApiService {
    @GET(RouteConstants.INDEX)
    Observable<Repo<Simple>> platformIndex();

    @GET(RouteConstants.STEADY_INFO)
    Observable<Repo<String>> getSteadyInfo(@QueryMap Map<String, String> param);
}
