package com.mvp_dagger_retrofit.common.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;


/**
 * Created by niuxiaowei on 2015/11/18.
 */
public class UIThreadExecutor implements Executor {
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    public void execute(Runnable command) {
        mHandler.post(command);
    }
}
