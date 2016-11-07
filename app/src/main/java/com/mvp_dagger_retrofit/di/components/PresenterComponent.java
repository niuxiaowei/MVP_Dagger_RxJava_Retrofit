package com.mvp_dagger_retrofit.di.components;

import com.mvp_dagger_retrofit.di.modules.PresenterModule;
import com.mvp_dagger_retrofit.di.scopes.PerActivity;
import com.mvp_dagger_retrofit.presenter.MainPresenterImp;

import dagger.Component;

/**
 * 负责对presenter进行注入
 * Created by niuxiaowei on 16/11/5.
 */
@PerActivity
@Component(dependencies = {MDRAppComponent.class} ,modules = {PresenterModule.class})
public interface PresenterComponent {

    void inject(MainPresenterImp mainPresenterImp);
}
