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


        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        testLayout = (LinearLayout) findViewById(R.id.test_layout);
        dis1 = (GridImagesDisplay) findViewById(R.id.dis_1);
        dis2 = (GridImagesDisplay) findViewById(R.id.dis_2);
        dis3 = (GridImagesDisplay) findViewById(R.id.dis_3);
        dis4 = (GridImagesDisplay) findViewById(R.id.dis_4);
        dis5 = (GridImagesDisplay) findViewById(R.id.dis_5);
        loadMoreListContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_container);
        loadMoreSmallImageListView = (ListView) findViewById(R.id.load_more_small_image_list_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title);

    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {
        toolbar.setOnClickListener(new View.OnClickListener() {
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
