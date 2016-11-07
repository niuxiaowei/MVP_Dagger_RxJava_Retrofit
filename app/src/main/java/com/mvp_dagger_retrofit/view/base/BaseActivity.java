package com.mvp_dagger_retrofit.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.mvp_dagger_retrofit.di.components.IGetAppComponent;
import com.mvp_dagger_retrofit.di.components.MDRAppComponent;
import com.mvp_dagger_retrofit.di.modules.ActivityModule;
import com.mvp_dagger_retrofit.presenter.base.IPresenter;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by niuxiaowei on 16/11/5.
 */
public abstract class BaseActivity extends FragmentActivity {

    /**
     * 为fragment设置functions，具体实现子类来做
     * @param fragmentId
     */
    public void setFunctionsForFragment(int fragmentId){}
    /**
     * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

//    /**
//     * 初始化view，由子类进行实现
//     * @param savedInstanceState
//     */
//    protected abstract void initViews(@Nullable Bundle savedInstanceState);
//
//    /**
//     * 初始化数据，由子类进行实现
//     * @param savedInstanceState
//     */
//    protected abstract void initData(@Nullable Bundle savedInstanceState);
    /**
     * 由子类来实现
     */
    protected void initializeInjector(){

    }

    private Set<IPresenter> mAllIPresenters = new HashSet<IPresenter>(1);



    private void addPresenters(IPresenter[] IPresenters){
        if(IPresenters != null && IPresenters.length > 0){
            for (int i = 0; i < IPresenters.length; i++) {

                mAllIPresenters.add(IPresenters[i]);
            }
        }
    }

    /**
     * 子类来实现该方法，子类把自己拥有的{@link IPresenter}组成一个数组，提供给父类
     */
    protected abstract IPresenter[] onGetPresenters();

    /**
     * 初始化{@link IPresenter},需要子类来实现
     */
    protected abstract void onInitPresents();

    /**
     * 从intent中解析数据，具体子类来实现
     * @param argIntent
     */
    protected void parseArgumentsFromIntent(Intent argIntent){

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if(getIntent() != null){
            parseArgumentsFromIntent(getIntent());
        }
        initializeInjector();
        addPresenters(onGetPresenters());
        onInitPresents();

    }




    /**
     * Get the Main Application component for dependency injection.
     *
     */
    public MDRAppComponent getApplicationComponent() {
        return ((IGetAppComponent)getApplication()).getDidiAppComponent();
    }

    public ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onResume();
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onPause();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onDestroy();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onStop();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onStart();
            }
        }
    }

}
