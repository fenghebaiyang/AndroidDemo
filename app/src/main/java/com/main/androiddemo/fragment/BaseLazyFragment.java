package com.main.androiddemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <br/> Description:懒加载的fragment
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/5/11 0011
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public abstract class BaseLazyFragment extends Fragment {

    public Context mContext;
    /**
     * 无布局内容
     */
    private static final int NO_LAYOUT_CONTENT = 0;
    /**
     * 根视图
     */
    public View viewParent;

    private boolean isPrepared = false;
    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    protected boolean isLoadDataCompleted = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                if (isPrepared && !isLoadDataCompleted) {
                    isLoadDataCompleted = true;
                    init();
                }
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID = getContentViewId();
        if (NO_LAYOUT_CONTENT != layoutID) {
            viewParent = inflater.inflate(layoutID, container, false);
            initPrepare();
            return viewParent;
        }
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            isLoadDataCompleted = true;
            init();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }


    protected synchronized void initPrepare() {
        if (!isPrepared) {
            onFirstUserVisible();
            isPrepared = true;
        }
    }

    /**
     * 获取上一个界面传送过来的数据
     */
    protected abstract void initGetData();
    /**
     * @return 获取layoutID
     */
    public abstract int getContentViewId();

    /**
     * 查找视图
     */
    public abstract void findViews();

    /**
     * 注册监听事件
     */
    public abstract void widgetListener();

    /**
     * 初始化的一些操作
     */
    protected abstract void init();

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    public void onFirstUserVisible() {
        initGetData();
        findViews();
        widgetListener();
    }

    /**
     * fragment可见（切换回来或者onResume）
     */
    public void onUserVisible() {

    }

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    public void onFirstUserInvisible() {

    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    public void onUserInvisible() {

    }

}