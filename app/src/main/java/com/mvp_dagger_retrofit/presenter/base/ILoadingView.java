package com.mvp_dagger_retrofit.presenter.base;

/**
 * 加载中抽象view
 * Created by niuxiaowei on 16/4/5.
 */
public interface ILoadingView<L> {
    /**
     * 显示加载数据view
     * @param loadingContent
     */
    void showLoadingView(L loadingContent);

    /**
     * 隐藏加载数据view
     */
    void hideLoadingView();
}
