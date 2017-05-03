package com.main.androiddemo.widget.refreshlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.main.androiddemo.widget.loadmore.LoadMoreDefaultFooterView;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/2 0002
 */
public class ExListView extends LinearLayout {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private LoadMoreDefaultFooterView footerView;

    private ExRefreshLoadListener refreshLoadListener;
    private AbsListView.OnScrollListener mOnScrollListener;

    private boolean mIsLoading;
    private boolean mHasMore = false;
    private boolean mAutoLoadMore = true;
    private boolean mLoadError = false;

    private boolean mListEmpty = true;
    private boolean mShowLoadingForFirstPage = false;

    public ExListView(Context context) {
        this(context, null);
    }

    public ExListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setRefreshLoadListener(ExRefreshLoadListener refreshLoadListener) {
        this.refreshLoadListener = refreshLoadListener;
    }

    private void init() {
        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        listView = new ListView(getContext());
        swipeRefreshLayout.addView(listView);

        setOrientation(VERTICAL);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(swipeRefreshLayout, lp);

        footerView = new LoadMoreDefaultFooterView(getContext());

        listView.addFooterView(footerView);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private boolean mIsEnd = false;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (null != mOnScrollListener) {
                    mOnScrollListener.onScrollStateChanged(view, scrollState);
                }
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (mIsEnd) {
                        onReachBottom();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (null != mOnScrollListener) {
                    mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
                if (firstVisibleItem + visibleItemCount >= totalItemCount - 1) {
                    mIsEnd = true;
                } else {
                    mIsEnd = false;
                }
            }
        });
    }


    private void onReachBottom() {
        // if has error, just leave what it should be
        if (mLoadError) {
            return;
        }
        if (mAutoLoadMore) {
            tryToPerformLoadMore();
        } else {
            if (mHasMore) {
//                mLoadMoreUIHandler.onWaitToLoadMore(this);
            }
        }
    }


    private void tryToPerformLoadMore() {
        if (mIsLoading) {
            return;
        }

        // no more content and also not load for first page
        if (!mHasMore && !(mListEmpty && mShowLoadingForFirstPage)) {
            return;
        }

        mIsLoading = true;

//        if (mLoadMoreUIHandler != null) {
//            mLoadMoreUIHandler.onLoading(this);
//        }
//        if (null != mLoadMoreHandler) {
//            mLoadMoreHandler.onLoadMore(this);
//        }
    }

    public void setShowLoadingForFirstPage(boolean showLoading) {
        mShowLoadingForFirstPage = showLoading;
    }

    public void setAutoLoadMore(boolean autoLoadMore) {
        mAutoLoadMore = autoLoadMore;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        mOnScrollListener = l;
    }

    /**
     * page has loaded
     *
     * @param emptyResult
     * @param hasMore
     */
    public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
        mLoadError = false;
        mListEmpty = emptyResult;
        mIsLoading = false;
        mHasMore = hasMore;

//        if (mLoadMoreUIHandler != null) {
//            mLoadMoreUIHandler.onLoadFinish(this, emptyResult, hasMore);
//        }
    }

    public void loadMoreError(int errorCode, String errorMessage) {
        mIsLoading = false;
        mLoadError = true;
//        if (mLoadMoreUIHandler != null) {
//            mLoadMoreUIHandler.onLoadError(this, errorCode, errorMessage);
//        }
    }
}
