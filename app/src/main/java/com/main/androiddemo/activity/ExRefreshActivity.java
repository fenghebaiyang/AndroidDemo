package com.main.androiddemo.activity;

import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.main.androiddemo.R;
import com.main.androiddemo.adapter.BaseXAdapter;
import com.main.androiddemo.widget.ProportionImageView;
import com.main.androiddemo.widget.refreshlayout.ExListView;
import com.main.androiddemo.widget.refreshlayout.ExRefreshLoadListener;

import java.util.Arrays;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/5/4 0004
 */
public class ExRefreshActivity extends BaseActivity {
    private ExListView exListView;

    String[] urls = new String[]{
            "http://img.hb.aicdn.com/e1fe0d8308a67797a0be276fde912e92d657d30b3552f-lgAhm3",
            "http://img.hb.aicdn.com/b38ccc6b047921809f8d5728dca4bef3f17b833a7a431-zoLH5g",
            "http://img.hb.aicdn.com/b06bc4d0823ea1b655cf0e39262d0a1df7b43c68598d7-lZ0Z1G",
            "http://img.hb.aicdn.com/6bb006109e0738f142cece8232a3a4b934508219186f2-oEqyMF",
            "http://img.hb.aicdn.com/32bf5280c858bfc9174b4c719197de672caa5d6a22f82-Zv7Ad6",
            "http://img.hb.aicdn.com/d9568c1a536baebd99559bda369247e9fac287febbc11-u0GNgZ",
            "http://img.hb.aicdn.com/884644fc6ca4ebfe1cbbb9d638c50c26c4c422943dc8d-mhBQHL",
            "http://img.hb.aicdn.com/1f4373bd1f6f3db3033bd6eaa2722213e60d4539aa6a5-MaAj5e",
            "http://img.hb.aicdn.com/68ab513d715b957578a5b87983bfe1f31f8c38331887f-NfTVSC",
            "http://img.hb.aicdn.com/98db6fc01d5cc3e8438d3242a385b4b1284ad16b192c6-ZktnFx",
            "http://img.hb.aicdn.com/8bcfa18ef562f6546c50a78309b0aaed21fae85a14f2b-sxRHII",
            "http://img.hb.aicdn.com/ed54feb5f91e62b7f3f0dd217324a8aeedebefd0dce1d-cHyijV",
            "http://img.hb.aicdn.com/180a5cbc12bc5ed57c8be4507698e1dcd02a02a02af31-5IyICC",
            "http://img.hb.aicdn.com/216e5a705a3ec0145687e30ed742f3223cd2fab21b43c-RsDKfP",
            "http://img.hb.aicdn.com/eb3c9abccdb2651948f7a264c81b57f852a78ac32211c-GMSAMe",
            "http://img.hb.aicdn.com/67e9cbb1ccd1468f17af53b9b0eafd97ff5adde34600a-MNbNpT",
            "http://img.hb.aicdn.com/f0ff2d5f6fdaa7252c5c6e711b206e7ddca8838096d95-9F50Yg",
            "http://img.hb.aicdn.com/d86e36ff3ef866eef35739010203b757b47595d6299c4-mhh0Lo",
            "http://img.hb.aicdn.com/aa80969c4754c15f261811853dcca1faeb5146ce953c8-z7NVqP",
            "http://img.hb.aicdn.com/e42e50cd60c14687160ed77d3571a3afb79a76528b315-1jAdHT",
    };

    BaseXAdapter adapter;

    @Override
    protected void initGetData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.act_ex_refresh;
    }

    @Override
    protected void findViews() {
        exListView = (ExListView) findViewById(R.id.ex_list_view);
    }

    @Override
    protected void widgetListener() {
        exListView.setRefreshLoadListener(new ExRefreshLoadListener() {
            @Override
            public void onRefresh() {
                exListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.deleteAll();
                        adapter.addAll(Arrays.asList(urls));
                        exListView.getSwipeRefreshLayout().setRefreshing(false);
                    }
                }, 3000);
            }

            @Override
            public void onLoad() {
                exListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(Arrays.asList(urls));
                        exListView.loadMoreFinish(false, true);
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void init() {
        adapter = new BaseXAdapter<String>(this) {
            @Override
            public int getConvertViewRes(int position, int type) {
                return R.layout.item_grid_images_display;
            }

            @Override
            public View getItemView(int position, View convertView, ViewGroup parent, ViewHolder viewHolder) {
                ProportionImageView imageView = viewHolder.getView(R.id.img);
                Glide.with(mContext).load(list.get(position)).into(imageView);
                return convertView;
            }
        };
        //adapter.addAll(Arrays.asList(urls));
        exListView.getListView().setAdapter(adapter);
    }
}
