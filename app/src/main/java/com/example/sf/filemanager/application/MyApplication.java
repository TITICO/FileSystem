package com.example.sf.filemanager.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by 89003337 on 2017/4/11.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        this.mInstance=this;
    }

    public static MyApplication getmInstance() {
        return mInstance;
    }
}
