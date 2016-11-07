package com.mvp_dagger_retrofit.model.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by niuxiaowei on 16/10/9.
 */
public class RetrofitFactory {

    private Retrofit mRetrofit;


    RetrofitFactory(OkHttpClient okHttpClient) {

        mRetrofit = createRetrofit(okHttpClient);
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(UrlUtil.NEWS_BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient);
        return builder.build();
    }


    public <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public <S> S createService(Class<S> serviceClass, final String authToken) {
        return mRetrofit.create(serviceClass);
    }
}
