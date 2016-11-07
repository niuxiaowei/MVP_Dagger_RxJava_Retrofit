package com.mvp_dagger_retrofit.model.entities;

/**
 * 所有entity的父类,抽象了子类公用的几个方法
 * Created by niuxiaowei on 16/11/7.
 */

public abstract class BaseEntity {

    /**
     * 成功的状态码
     */
    protected static final int SUCCESS_CODE = 0;
    /**
     * 是否成功
     * @return
     */
    public abstract boolean isSuccess();
}
