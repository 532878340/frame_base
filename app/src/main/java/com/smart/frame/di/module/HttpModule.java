package com.smart.frame.di.module;

import com.smart.frame.di.annotation.qualifier.ApiUrl;
import com.smart.frame.di.annotation.scope.ApplicationScope;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.http.api.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http module
 *
 * @author Gjm
 * @date 2018/01/12
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
    ApiService provideApiService(@ApiUrl Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @ApplicationScope
    @Provides
    OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Configs.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Configs.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(chain -> {
                    Request origin = chain.request();
                    Request request = origin.newBuilder()
                            .header("imei", "123456")
                            .method(origin.method(), origin.body())
                            .build();

                    return chain.proceed(request);
                })
                .build();
    }

    /**
     * 创建对应的Retrofit，根据retrofit返回ApiService
     */
    private Retrofit createRetrofit(OkHttpClient client, String rootUrl) {
        return new Retrofit.Builder()
                .baseUrl(rootUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
