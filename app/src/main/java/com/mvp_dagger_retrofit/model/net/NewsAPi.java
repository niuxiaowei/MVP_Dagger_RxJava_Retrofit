package com.mvp_dagger_retrofit.model.net;

import com.mvp_dagger_retrofit.model.entities.NewsChannelsEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 新闻api
 * Created by niuxiaowei on 16/11/4.
 */

public interface NewsAPi {

    @GET("/109-34")
    Observable<NewsChannelsEntity> newsChannels();
}
