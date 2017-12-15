package com.smart.frame.di.module;

import com.smart.frame.di.annotation.qualifier.ApiUrl;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.http.api.ApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: http module
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */
@Module
public class HttpModule {
    @Provides
    @ApplicationScope
    @ApiUrl
    Retrofit provideApiServiceRetrofit(OkHttpClient client) {
        return createRetrofit(client, Configs.BASE_URL);
    }

    @Provides
    @ApplicationScope
    ApiService provideApiService(@ApiUrl Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @ApplicationScope
    @Provides
    OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Configs.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Configs.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * 创建对应的Retrofit，根据retrofit返回ApiService
     */
    private Retrofit createRetrofit(OkHttpClient client,String rootUrl){
        return new Retrofit.Builder()
                .baseUrl(rootUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
