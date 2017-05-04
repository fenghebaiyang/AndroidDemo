package com.main.androiddemo.widget.refreshlayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/4 0004
 */
public class ExRecyclerView extends LinearLayout {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ExRefreshLoadListener refreshLoadListener;

    private boolean mIsLoading;
    private boolean mHasMore = false;
    private boolean mAutoLoadMore = true;
    private boolean mLoadError = false;

    private boolean mListEmpty = true;
    private boolean mShowLoadingForFirstPage = false;

    public ExRecyclerView(Context context) {
        this(context, null);
    }

    public ExRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        recyclerView = new RecyclerView(getContext());
        swipeRefreshLayout.addView(recyclerView);

        setOrientation(VERTICAL);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(swipeRefreshLayout, lp);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (refreshLoadListener != null) {
                    refreshLoadListener.onRefresh();
                }
            }
        });
//        swipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
//            @Override
//            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
//                return !mIsLoading;
//            }
//        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动，既是否向右滑动或向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = 0;
                    int totalItemCount = 0;
                    if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                        int[] into = manager.findLastVisibleItemPositions(null);
                        for (int i = 0; i < into.length; i++) {
                            if (lastVisibleItem < into[i]) {
                                lastVisibleItem = into[i];
                            }
                        }
                        totalItemCount = manager.getItemCount();
                    } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        lastVisibleItem = manager.findLastVisibleItemPosition();
                        totalItemCount = manager.getItemCount();
                    }
                    if (totalItemCount > 0 && lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        onReachBottom();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                /*if (dx > 0) {
                    //大于0表示，正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0 表示停止或向左滚动
                    isSlidingToLast = false;
                }*/
                if (dy > 0) {
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
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

        if (refreshLoadListener != null) {
            refreshLoadListener.onLoad();
        }
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

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }
}
