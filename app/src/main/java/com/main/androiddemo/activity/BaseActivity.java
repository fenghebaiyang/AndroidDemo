package com.main.androiddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.finalteam.toolsfinal.ActivityManager;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/6/15 0015
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityManager.getActivityManager().addActivity(this);
        // TODO: 2016/12/22 0022 popManagerStack
        setContentView(getContentViewId());
        findViews();
        initGetData();
        widgetListener();
        init();
    }

    protected abstract void initGetData();

    protected abstract int getContentViewId();

    protected abstract void findViews();

    protected abstract void widgetListener();

    protected abstract void init();
}
