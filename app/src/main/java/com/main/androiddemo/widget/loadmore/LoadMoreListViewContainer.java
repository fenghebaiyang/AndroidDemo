package com.main.androiddemo.widget.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/7/29 0029
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public class LoadMoreListViewContainer extends LoadMoreContainerBase {

    private ListView mListView;

    public LoadMoreListViewContainer(Context context) {
        super(context);
    }

    public LoadMoreListViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void addFooterView(View view) {
        mListView.addFooterView(view);
    }

    @Override
    protected void removeFooterView(View view) {
        mListView.removeFooterView(view);
    }

    @Override
    protected AbsListView retrieveAbsListView() {
        mListView = (ListView) getChildAt(0);
        return mListView;
    }
}
