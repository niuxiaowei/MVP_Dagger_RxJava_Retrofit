package com.mvp_dagger_retrofit.presenter;

import com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity;
import com.mvp_dagger_retrofit.presenter.base.ILoadingView;
import com.mvp_dagger_retrofit.presenter.base.IView;
import com.mvp_dagger_retrofit.presenter.base.IPresenter;
import com.mvp_dagger_retrofit.presenter.bean.NewsChannelsBean;

/**
 * 主页合约类
 * Created by niuxiaowei on 16/11/5.
 */

public class MainContract {

    public interface IMainView extends IView,ILoadingView<String>{

        void onGetNewsChannels(NewsChannelsBean newsChannelsBean);
        void onGetNewsChannelsFailed();
    }

    public interface IMainPresenter extends IPresenter<IMainView> {

        void getNewsChannels();
    }
}
