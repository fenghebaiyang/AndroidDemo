package com.main.androiddemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <br/> Description:fragment基类
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/6/15 0015
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID = getContentViewId();
        if (NO_LAYOUT_CONTENT != layoutID) {
            viewParent = inflater.inflate(layoutID, container, false);
            return viewParent;
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGetData();
        findViews();
        widgetListener();
        init();
    }

    /**
     * 无布局内容
     */
    private static final int NO_LAYOUT_CONTENT = 0;
    /**
     * 根视图
     */
    public View viewParent;

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initGetData();

    /**
     * @return 获取layoutID
     */
    protected abstract int getContentViewId();

    /**
     * 查找视图
     */
    protected abstract void findViews();

    /**
     * 注册监听事件
     */
    protected abstract void widgetListener();

    /**
     * 初始化的一些操作
     */
    protected abstract void init();
}
