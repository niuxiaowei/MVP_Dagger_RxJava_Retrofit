package com.mvp_dagger_retrofit.model.net;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;

/**
 * Created by niuxiaowei on 16/10/9.
 */
@Singleton
public class ApiFactory {

    private NewsAPi mNewsAPi;
    private RetrofitFactory mRetrofitFactory ;
    private OKHttpFactory mOKHttpFactory;

    @Inject
    public ApiFactory() {
        mOKHttpFactory = new OKHttpFactory();
    }

    /**
     * 必须调用该方法进行初始化,否则不能使用
     * @param interceptors okhttp的拦截器
     *
     */
    public void init(Interceptor ... interceptors){
        mOKHttpFactory.addInterceptor(interceptors);
        mRetrofitFactory = new RetrofitFactory(mOKHttpFactory.getOkHttpClient());
        mNewsAPi = mRetrofitFactory.createService(NewsAPi.class);
    }

    public NewsAPi getNewsAPi() {
        if(mNewsAPi == null){
            throw new RuntimeException("必须调用ApiFactory的init方法进行初始化");
        }
        return mNewsAPi;
    }
}
