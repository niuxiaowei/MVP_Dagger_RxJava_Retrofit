package com.mvp_dagger_retrofit.presenter.bean.mapping;

import com.mvp_dagger_retrofit.di.scopes.PerActivity;
import com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity;
import com.mvp_dagger_retrofit.presenter.bean.NewsChannelsBean;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * {@link com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity}与{@link com.mvp_dagger_retrofit.presenter.bean.NewsChannelsBean}
 * 之间进行转换
 * Created by niuxiaowei on 16/11/7.
 */
@PerActivity
public class NewsChannelsBeanMap {

    @Inject
    public NewsChannelsBeanMap() {
    }

    public Observable<NewsChannelsBean> map(NewsChannelsEntity newsChannelsEntity){
        if(newsChannelsEntity == null || newsChannelsEntity.getShowapi_res_body() == null){
            return null;
        }

        List<NewsChannelsEntity.ShowapiResBodyEntity.ChannelListBean> channelListBeanList = newsChannelsEntity.getShowapi_res_body().getChannelList();
        if(channelListBeanList != null){
            NewsChannelsBean newsChannelsBean = new NewsChannelsBean();
            for (NewsChannelsEntity.ShowapiResBodyEntity.ChannelListBean bean:channelListBeanList
                 ) {
                newsChannelsBean.addNewsChannel(new NewsChannelsBean.NewsChannelBean(bean.getChannelId(),bean.getName()));
            }
            return Observable.just(newsChannelsBean);
        }
        return null;

    }
}
