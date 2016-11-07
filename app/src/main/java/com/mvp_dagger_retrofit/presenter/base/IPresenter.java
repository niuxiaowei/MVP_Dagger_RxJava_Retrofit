package com.mvp_dagger_retrofit.presenter.base;

/**
 * Created by niuxiaowei on 2015/11/18.
 */
public interface IPresenter<V extends IView> {


     void onStop();

     void onResume();

     void onDestroy();

    void onPause();

    void onStart();

    void init(V v);
}
