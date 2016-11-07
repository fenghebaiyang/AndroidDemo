package com.main.androiddemo.base;

import android.app.Application;

/**
 * 全局应用程序类
 * Created by justme on 15/8/24.
 */
public class App extends Application {

    private static App ourInstance;

    public static App getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
