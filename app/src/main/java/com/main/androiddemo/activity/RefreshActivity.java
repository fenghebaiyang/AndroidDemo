package com.main.androiddemo.activity;

import com.main.androiddemo.R;
import com.main.androiddemo.widget.refreshlayout.SwipeRefreshXLayout;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/4/10 0010
 */
public class RefreshActivity extends BaseActivity {

    private SwipeRefreshXLayout swipeRef;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_refresh;
    }

    @Override
    protected void findViews() {


        swipeRef = (SwipeRefreshXLayout) findViewById(R.id.swipeRef);


    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {
        swipeRef.setOnRefreshListener(new SwipeRefreshXLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRef.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRef.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        swipeRef.setOnPullUpRefreshListener(new SwipeRefreshXLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                swipeRef.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRef.setPullUpRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void init() {
        swipeRef.setMode(SwipeRefreshXLayout.Mode.BOTH);
    }
}
