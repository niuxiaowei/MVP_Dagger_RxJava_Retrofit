package com.mvp_dagger_retrofit.presenter;

import android.util.Log;

import com.mvp_dagger_retrofit.common.executor.NormalThreadExecutor;
import com.mvp_dagger_retrofit.common.executor.UIThreadExecutor;
import com.mvp_dagger_retrofit.di.components.DaggerPresenterComponent;
import com.mvp_dagger_retrofit.di.components.IGetAppComponent;
import com.mvp_dagger_retrofit.di.modules.PresenterModule;
import com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity;
import com.mvp_dagger_retrofit.model.net.ApiFactory;
import com.mvp_dagger_retrofit.presenter.base.BasePresenter;
import com.mvp_dagger_retrofit.presenter.bean.NewsChannelsBean;
import com.mvp_dagger_retrofit.presenter.bean.mapping.NewsChannelsBeanMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by niuxiaowei on 16/11/6.
 */

public class MainPresenterImp extends BasePresenter implements MainContract.IMainPresenter {

    private MainContract.IMainView mMainView;

    @Inject
    ApiFactory mApiFactory;
    @Inject
    NormalThreadExecutor mNormalThreadExecutor;
    @Inject
    UIThreadExecutor mUIThreadExecutor;
    @Inject
    NewsChannelsBeanMap mNewsChannelsBeanMap;

    public MainPresenterImp(IGetAppComponent iGetAppComponent) {
        DaggerPresenterComponent.builder().mDRAppComponent(iGetAppComponent.getDidiAppComponent()).presenterModule(new PresenterModule()).build().inject(this);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void init(MainContract.IMainView iMainView) {

        mMainView = iMainView;
        mMainView.initView();
    }

    @Override
    public void getNewsChannels() {
        mMainView.showLoadingView("获取数据中......");
         mApiFactory.getNewsAPi().newsChannels().subscribeOn(Schedulers.from(mNormalThreadExecutor))
         .observeOn(Schedulers.from(mUIThreadExecutor))
                 .flatMap(new Func1<NewsChannelsEntity, Observable<NewsChannelsBean>>() {
                     @Override
                     public Observable<NewsChannelsBean> call(NewsChannelsEntity newsChannelsEntity) {
                         if(newsChannelsEntity != null && newsChannelsEntity.isSuccess()){
                             return mNewsChannelsBeanMap.map(newsChannelsEntity);
                         }
                         return null;
                     }
                 })
         .subscribe(new Subscriber<NewsChannelsBean>() {
             @Override
             public void onCompleted() {
             }

             @Override
             public void onError(Throwable e) {
                 mMainView.onGetNewsChannelsFailed();
             }

             @Override
             public void onNext(NewsChannelsBean newsChannelsBean) {

                 mMainView.onGetNewsChannels(newsChannelsBean);
             }
         });
    }
}
