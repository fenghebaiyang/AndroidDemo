package com.main.androiddemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基础fragment
 *
 * @Description
 * @author 覃励
 * @date 2015/12/1
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public abstract class BaseFragment extends Fragment {
    private boolean isPrepared;
    //第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private boolean isFirstResume = true;
    private boolean isFirstInvisible = true;
    private boolean isFirstVisible = true;
    protected View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID = setLayout();
        if (NO_LAYOUT_CONTENT != layoutID) {
            fragmentView = inflater.inflate(layoutID, container, false);
            return fragmentView;
        }
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
        init();
        registerEvent();
    }

    /**
     * 设置布局
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected abstract int setLayout();

    /**
     * 初始化
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected abstract void init();

    /**
     * 注册事件
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected abstract void registerEvent();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
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

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected void onFirstUserVisible(){
    }

    /**
     * fragment可见（切换回来或者onResume）
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected void onUserVisible(){
    }

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected void onFirstUserInvisible(){
    }

    /**
     * fragment不可见（切换掉或者onPause）
     *
     * @version 1.0
     * @createTime 2015/11/20
     * @updateTime 2015/11/20
     * @createAuthor 覃励
     * @updateAuthor 覃励
     * @updateInfo
     */
    protected void onUserInvisible(){
    }

    /**
     * 无布局内容
     */
    private static final int NO_LAYOUT_CONTENT = 0;
}
