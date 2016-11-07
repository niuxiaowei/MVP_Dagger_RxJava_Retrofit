package com.mvp_dagger_retrofit.view.util;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by niuxiaowei on 2015/10/16.
 * toast工具類
 */
@Singleton
public class Toaster {
    public  boolean mIsShow = true;
    //當前正在顯示的toast
    private  Toast mToast;
    private Context mContext;

    @Inject
    public Toaster(Context context) {
        mContext = context;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public  void showShort( CharSequence message)
    {
        cancelCurrentToast();
        if (mIsShow){
            mToast =  Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            mToast.show();
        }

    }

    //取消當前正在顯示的toast
    private  void cancelCurrentToast(){
        if(mToast != null){
            mToast.cancel();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public  void showShort(int message)
    {
        cancelCurrentToast();
        if (mIsShow){

            mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public  void showLong(CharSequence message)
    {
        cancelCurrentToast();
        if (mIsShow){

           mToast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
            mToast.show();
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public  void showLong(int message)
    {
        cancelCurrentToast();
        if (mIsShow){

           mToast =  Toast.makeText(mContext, message, Toast.LENGTH_LONG);
            mToast.show();
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public  void show( CharSequence message, int duration)
    {
        cancelCurrentToast();
        if (mIsShow){

            mToast =  Toast.makeText(mContext, message, duration);
            mToast.show();
        }

    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public  void show(int message, int duration)
    {
        cancelCurrentToast();
        if (mIsShow){
           mToast =  Toast.makeText(mContext, message, duration);

            mToast.show();
        }
    }

    public  void desotry(){
        mToast = null;
    }
}
