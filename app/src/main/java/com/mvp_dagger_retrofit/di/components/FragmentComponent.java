package com.mvp_dagger_retrofit.di.components;

import com.mvp_dagger_retrofit.di.modules.ActivityModule;
import com.mvp_dagger_retrofit.di.scopes.PerActivity;

import dagger.Component;

/**
 * 注入fragments的component
 * Created by niuxiaowei on 16/5/25.
 */
@PerActivity
@Component(dependencies = MDRAppComponent.class,modules = {ActivityModule.class})
public interface FragmentComponent  {
}
