package com.main.androiddemo.widget.refreshlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * <br/> Description:上下拉刷新控件
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/4/10 0010
 */
public class ExRefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {

    public ExRefreshLayout(Context context) {
        super(context);
    }

    public ExRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


}
