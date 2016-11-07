package com.mvp_dagger_retrofit.di.components;

/**
 * 获取{@link MDRAppComponent}的接口，创建此接口主要目的是为类让model层的类在获取{@link MDRAppComponent}
 * 时，不要依赖于具体的{@link android.app.Application},而是依赖于接口
 * Created by niuxiaowei on 16/8/29.
 */
public interface IGetAppComponent {
    /**
     * 获取{@link MDRAppComponent}
     * @return
     */
    MDRAppComponent getDidiAppComponent();
}
