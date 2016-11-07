package com.mvp_dagger_retrofit.di.modules;

import android.content.Context;

import com.mvp_dagger_retrofit.common.executor.NormalThreadExecutor;
import com.mvp_dagger_retrofit.common.executor.UIThreadExecutor;
import com.mvp_dagger_retrofit.di.components.IGetAppComponent;
import com.mvp_dagger_retrofit.di.components.MDRAppComponent;
import com.mvp_dagger_retrofit.model.net.ApiFactory;
import com.mvp_dagger_retrofit.view.util.Navigator;
import com.mvp_dagger_retrofit.view.util.Toaster;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * dagger2的app module,主要提供了app的所有的单利对象
 * Created by niuxiaowei on 16/11/5.
 */
@Module
public class MDRAppModule {

    private Context mContext;

    public MDRAppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public IGetAppComponent provideIGetAppComponent() {
        if (mContext instanceof IGetAppComponent) {
            return ((IGetAppComponent) mContext);
        }
        return null;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public Toaster provideToaster(Context context) {
        return new Toaster(context);
    }

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    public NormalThreadExecutor provideNormalThreadExecutor() {
        return new NormalThreadExecutor();
    }

    @Provides
    @Singleton
    public UIThreadExecutor provideUIThreadExecutor() {
        return new UIThreadExecutor();
    }

    @Provides
    @Singleton
    public ApiFactory provideApiFactory(){
        return new ApiFactory();
    }
}
