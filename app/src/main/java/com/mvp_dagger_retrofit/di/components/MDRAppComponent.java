package com.mvp_dagger_retrofit.di.components;

import android.content.Context;

import com.mvp_dagger_retrofit.MDRApp;
import com.mvp_dagger_retrofit.common.executor.NormalThreadExecutor;
import com.mvp_dagger_retrofit.common.executor.UIThreadExecutor;
import com.mvp_dagger_retrofit.common.rxbus.RxBus;
import com.mvp_dagger_retrofit.di.modules.MDRAppModule;
import com.mvp_dagger_retrofit.model.net.ApiFactory;
import com.mvp_dagger_retrofit.view.util.Navigator;
import com.mvp_dagger_retrofit.view.util.Toaster;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 注入app的所有单例对象
 * Created by niuxiaowei on 16/11/5.
 */
@Singleton
@Component(modules = MDRAppModule.class)
public interface MDRAppComponent {
    void inject(MDRApp mdrApp);

    /*给子类Component暴漏的接口*/
    Context getContext();
    Toaster getToaster();
    Navigator getNavigator();
    RxBus getRxBus();
    NormalThreadExecutor getNormalTheadExecutor();
    UIThreadExecutor getUIThreadExecutor();

    IGetAppComponent getIGetAppComponent();
    ApiFactory getApiFactory();
}
