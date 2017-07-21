package com.frame.di;

import com.frame.di.annotation.ApplicationScope;
import com.frame.manager.ApiService;
import com.frame.manager.constants.Configs;
import com.frame.manager.constants.Constants;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zijin on 2017/7/20.
 * Email:info@zijinqianbao.com
 */

@Module
public class ApiServiceModule {
    @ApplicationScope
    @Provides
    ApiService getApiService(CallAdapter.Factory callAdapterFactory, Converter.Factory converterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Configs.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
                .create(ApiService.class);
    }

    @ApplicationScope
    @Provides
    OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    @ApplicationScope
    @Provides
    CallAdapter.Factory getCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @ApplicationScope
    @Provides
    Converter.Factory getConvertFactory() {
        return GsonConverterFactory.create();
    }
}
