package com.mvp_dagger_retrofit.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp_dagger_retrofit.di.components.MDRAppComponent;
import com.mvp_dagger_retrofit.di.modules.ActivityModule;
import com.mvp_dagger_retrofit.presenter.base.IPresenter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by niuxiaowei on 16/11/5.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mBaseActivity;

    protected Functions mFunctions;

    public void setFunctions(Functions functions){
        this.mFunctions = functions;
    }

    /**
     * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();


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
     * 从{@link Bundle}中解析数据，具体子类来实现
     * @param args
     */
    protected void parseArguments(Bundle args){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof BaseActivity){
            mBaseActivity = (BaseActivity)context;
            mBaseActivity.setFunctionsForFragment(getId());
        }
        initializeInjector();
        addPresenters(onGetPresenters());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(getArguments() != null){
            parseArguments(getArguments());
        }
        if(getLayoutResId() !=0){
            return inflater.inflate(getLayoutResId(),container,false);
        }else {

            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitPresents();

    }





    @Override
    public void onResume() {
        super.onResume();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onPause();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onDestroy();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onStop();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        for (IPresenter IPresenter : mAllIPresenters
                ) {
            if(IPresenter != null){
                IPresenter.onStart();
            }
        }
    }



    protected MDRAppComponent getApplicationComponent() {
        if(mBaseActivity != null){
            return mBaseActivity.getApplicationComponent();
        }
        return null;
    }

    protected ActivityModule getActivityModule(){
        if(mBaseActivity != null){
            return mBaseActivity.getActivityModule();
        }
        return null;
    }
}
