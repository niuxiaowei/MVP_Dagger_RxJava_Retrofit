package com.mvp_dagger_retrofit.model.net;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 创建okhttp的工厂类
 * Created by niuxiaowei on 16/10/9.
 */
public class OKHttpFactory {
    /*读写,链接的默认超时时间*/
    private final int READ_TIME_OUT = 15, WRITE_TIME_OUT = 15, CONNECT_TIME_OUT = 20;

    private OkHttpClient mOkHttpClient;

    private Set<Interceptor> mInterceptors;


    OKHttpFactory() {
    }


    /**
     * 添加多个拦截器
     *
     * @param interceptors
     */
    public void addInterceptor(Interceptor... interceptors) {
        if (interceptors == null) {
            return;
        }
        if (mInterceptors == null) {
            mInterceptors = new HashSet<>(interceptors.length);
        }
        for (Interceptor interceptor : interceptors
                ) {

            mInterceptors.add(interceptor);
        }
    }


    OkHttpClient getOkHttpClient() {


        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(interceptor);
        okBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);

        /*添加加入的拦截器*/
        for (Interceptor interceptor1 : mInterceptors
                ) {
            okBuilder.addInterceptor(interceptor1);
        }
        mOkHttpClient = okBuilder.build();
        return mOkHttpClient;
    }

}
