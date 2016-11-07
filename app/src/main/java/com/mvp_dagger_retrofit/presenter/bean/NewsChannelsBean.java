package com.mvp_dagger_retrofit.presenter.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应{@link com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity}类,创建该类的原因是,
 * {@link com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity}包含了一些上层不需要的信息
 * 比如状态码。上层是完全不需要关心这些信息的,因此创建该类,来屏蔽掉这些信息,上层使用者使用起来就很简单
 * Created by niuxiaowei on 16/11/7.
 */

public class NewsChannelsBean {

    private List<NewsChannelBean> mNewsChannels;

    /**
     * 代表单个新闻频道
     */
    public static class NewsChannelBean {

        /**
         * 新闻频道id
         */
        private String channelId;
        /**
         * 新闻频道名称
         */
        private String name;

        public NewsChannelBean() {
        }

        public NewsChannelBean(String channelId, String name) {
            this.channelId = channelId;
            this.name = name;
        }



        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<NewsChannelBean> getNewsChannels() {
        return mNewsChannels;
    }

    public void setNewsChannels(List<NewsChannelBean> newsChannels) {
        mNewsChannels = newsChannels;
    }

    public void addNewsChannel(NewsChannelBean newsChannelBean){
        if(newsChannelBean == null){
            return;
        }
        if(mNewsChannels == null){
            mNewsChannels = new ArrayList<>();
        }
        mNewsChannels.add(newsChannelBean);
    }

}
