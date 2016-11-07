package com.mvp_dagger_retrofit.di.modules;

import android.app.Activity;

import com.mvp_dagger_retrofit.di.components.IGetAppComponent;
import com.mvp_dagger_retrofit.di.components.MDRAppComponent;
import com.mvp_dagger_retrofit.di.scopes.PerActivity;
import com.mvp_dagger_retrofit.presenter.MainContract;
import com.mvp_dagger_retrofit.presenter.MainPresenterImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/5/16.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity provideActivity(){
        return mActivity;
    }

    @PerActivity
    @Provides
    MainContract.IMainPresenter provideMainPresenter(IGetAppComponent iGetAppComponent){
        return new MainPresenterImp(iGetAppComponent);
    }
}
