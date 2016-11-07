package com.mvp_dagger_retrofit.di.components;

import com.mvp_dagger_retrofit.di.modules.ActivityModule;
import com.mvp_dagger_retrofit.di.scopes.PerActivity;
import com.mvp_dagger_retrofit.view.MainActivity;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/5/16.
 */
@PerActivity
@Component(dependencies = MDRAppComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
