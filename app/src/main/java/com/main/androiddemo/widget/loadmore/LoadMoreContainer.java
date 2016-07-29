package com.main.androiddemo.widget.loadmore;

import android.view.View;
import android.widget.AbsListView;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/7/29 0029
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public interface LoadMoreContainer {

    public void setShowLoadingForFirstPage(boolean showLoading);

    public void setAutoLoadMore(boolean autoLoadMore);

    public void setOnScrollListener(AbsListView.OnScrollListener l);

    public void setLoadMoreView(View view);

    public void setLoadMoreUIHandler(LoadMoreUIHandler handler);

    public void setLoadMoreHandler(LoadMoreHandler handler);

    /**
     * When data has loaded
     *
     * @param emptyResult
     * @param hasMore
     */
    public void loadMoreFinish(boolean emptyResult, boolean hasMore);

    /**
     * When something unexpected happened while loading the data
     *
     * @param errorCode
     * @param errorMessage
     */
    public void loadMoreError(int errorCode, String errorMessage);
}