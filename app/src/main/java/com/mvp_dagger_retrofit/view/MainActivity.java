package com.mvp_dagger_retrofit.view;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.TextView;

import com.mvp_dagger_retrofit.R;
import com.mvp_dagger_retrofit.di.components.DaggerActivityComponent;
import com.mvp_dagger_retrofit.di.modules.ActivityModule;
import com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity;
import com.mvp_dagger_retrofit.presenter.MainContract;
import com.mvp_dagger_retrofit.presenter.base.IPresenter;
import com.mvp_dagger_retrofit.presenter.bean.NewsChannelsBean;
import com.mvp_dagger_retrofit.view.base.BaseActivity;
import com.mvp_dagger_retrofit.view.util.Toaster;

import javax.inject.Inject;

public class MainActivity extends BaseActivity  implements MainContract.IMainView {

    @Inject
    MainContract.IMainPresenter mMainPresenter;
    @Inject
    Toaster mToaster;

    private TextView mGetNewsChannels, mGetNewsChannelsResult;
    private ProgressDialog mProgressDialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected IPresenter[] onGetPresenters() {
        return new IPresenter[]{mMainPresenter};
    }

    @Override
    protected void onInitPresents() {
        mMainPresenter.init(this);
    }


    @Override
    protected void initializeInjector() {
        super.initializeInjector();
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).mDRAppComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initView() {

        mGetNewsChannels = (TextView)findViewById(R.id.get_news_channels);
        mGetNewsChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.getNewsChannels();
            }
        });

        mGetNewsChannelsResult = (TextView)findViewById(R.id.result);
    }

    @Override
    public void onGetNewsChannels(NewsChannelsBean newsChannelsBean) {
        hideLoadingView();
        mToaster.showLong("获取新闻频道成功");
        StringBuilder sb = new StringBuilder();
        for (NewsChannelsBean.NewsChannelBean bean :newsChannelsBean.getNewsChannels()
                ) {
            sb.append(bean.getName()+"\n");
        }
        mGetNewsChannelsResult.setText(sb.toString());
    }

    @Override
    public void onGetNewsChannelsFailed() {
        hideLoadingView();
        mToaster.showLong("获取新闻频道数据失败,请重新获取!");
    }

    @Override
    public void showLoadingView(String loadingContent) {
        if(mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.show();
    }

    @Override
    public void hideLoadingView() {

        if(mProgressDialog != null){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoadingView();
    }
}
