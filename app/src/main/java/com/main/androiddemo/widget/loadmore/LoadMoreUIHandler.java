package com.main.androiddemo.widget.loadmore;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/7/29 0029
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public interface LoadMoreUIHandler {
    public void onLoading(LoadMoreContainer container);

    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore);

    public void onWaitToLoadMore(LoadMoreContainer container);

    public void onLoadError(LoadMoreContainer container, int errorCode, String errorMessage);
}
