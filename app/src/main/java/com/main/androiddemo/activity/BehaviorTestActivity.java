package com.main.androiddemo.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.main.androiddemo.R;
import com.main.androiddemo.utils.StepLineManager;
import com.main.androiddemo.widget.GridImagesDisplay;
import com.main.androiddemo.widget.loadmore.LoadMoreListViewContainer;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/12/22 0022
 */
public class BehaviorTestActivity extends BaseActivity {

    private FrameLayout frameLayout;
    private LinearLayout testLayout;
    private GridImagesDisplay dis1;
    private GridImagesDisplay dis2;
    private GridImagesDisplay dis3;
    private GridImagesDisplay dis4;
    private GridImagesDisplay dis5;
    private LoadMoreListViewContainer loadMoreListContainer;
    private ListView loadMoreSmallImageListView;
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected int getContentViewId() {
        return R.layout.act_behavior_test;
    }

    @Override
    protected void findViews() {


        tvTitle = (TextView) findViewById(R.id.tv_title);

    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepLineManager.getInstance().nextStep(BehaviorTestActivity.this,null);
            }
        });
    }

    @Override
    protected void init() {

    }
}
