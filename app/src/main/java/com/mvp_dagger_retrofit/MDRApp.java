package com.mvp_dagger_retrofit;

import android.app.Application;

import com.mvp_dagger_retrofit.di.components.DaggerMDRAppComponent;
import com.mvp_dagger_retrofit.di.components.IGetAppComponent;
import com.mvp_dagger_retrofit.di.components.MDRAppComponent;
import com.mvp_dagger_retrofit.di.modules.MDRAppModule;
import com.mvp_dagger_retrofit.model.net.ApiFactory;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by niuxiaowei on 16/11/5.
 */

public class MDRApp extends Application implements IGetAppComponent{

    private MDRAppComponent mMDRAppComponent;

    @Inject
    ApiFactory mApiFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initApiFactory();
    }


    private void initializeInjector() {
        mMDRAppComponent = DaggerMDRAppComponent.builder().mDRAppModule(new MDRAppModule(getApplicationContext())).build();
        mMDRAppComponent.inject(this);
    }

    /**
     * 初始化api工厂类
     */
    private void initApiFactory(){

        /*在请求时,把系统参数加入所有的请求中*/
        Interceptor systemRequestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                HttpUrl url = request.url().newBuilder().addQueryParameter("showapi_appid","26725")
                        .addQueryParameter("showapi_sign","14f2e261b42146d4ad3b894ec38c3059")
                        .build();
//                        .addQueryParameter("showapi_timestamp")


                return chain.proceed(request.newBuilder().url(url).build());
            }
        };

        mApiFactory.init(systemRequestInterceptor);
    }

    @Override
    public MDRAppComponent getDidiAppComponent() {
        return mMDRAppComponent;
    }
}
