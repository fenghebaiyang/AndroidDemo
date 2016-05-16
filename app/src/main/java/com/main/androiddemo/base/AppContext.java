package com.main.androiddemo.base;

import android.app.Application;

/**
 * 全局应用程序类
 * Created by justme on 15/8/24.
 */
public class AppContext extends Application {

    private static AppContext ourInstance;

    public static AppContext getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
