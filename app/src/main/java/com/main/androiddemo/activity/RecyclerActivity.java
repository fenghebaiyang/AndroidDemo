package com.main.androiddemo.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.main.androiddemo.R;
import com.main.androiddemo.adapter.BaseXRecyclerAdapter;
import com.main.androiddemo.adapter.BaseXViewHolder;
import com.main.androiddemo.bean.HuaBanBean;
import com.main.androiddemo.utils.MathUtils;
import com.main.androiddemo.widget.AspectRatioImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaojianfeng
 * @description
 * @date 2017/11/16 0016 12:45
 */

public class RecyclerActivity extends AppCompatActivity {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private BaseXRecyclerAdapter adapter;
    private int maxPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(false);
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        //加载更多功能的代码
                        if (!refreshLayout.isRefreshing()) {
                            refreshLayout.setRefreshing(true);
                            getData(true);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private int space = 5;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space;
                }
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        adapter = new BaseXRecyclerAdapter<HuaBanBean.PinsBean>(this) {
            @Override
            public int getConvertViewRes(int viewType) {
                return R.layout.item_huaban;
            }

            @Override
            public void getItemView(BaseXViewHolder holder, int position) {
                String imgUrl = "";
                imgUrl = "http://hbimg.b0.upaiyun.com/" + list.get(position).getFile().getKey();
                AspectRatioImageView imageView = holder.getView(R.id.img_pro);
                imageView.setRatio(MathUtils.str2Float(list.get(position).getFile().getWidth()) / MathUtils.str2Float(list.get(position).getFile().getHeight()));
                Glide.with(mContext).load(imgUrl).asBitmap().into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                ((CardView) view.getParent()).setCardBackgroundColor(palette.getMutedColor(Color.parseColor("#ffffff")));
                            }
                        });
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

        getData(false);
    }

    private void getData(final boolean needMore) {
        String url = "http://huaban.com/partner/uc/aimeinv/pins/";
        if (needMore) {
            //加载更多
            url += "?max=" + maxPin;
        }
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                refreshLayout.setRefreshing(false);
                HuaBanBean bean = new Gson().fromJson(response, HuaBanBean.class);
                if (bean.getPins().size() > 0) {
                    maxPin = bean.getPins().get(bean.getPins().size() - 1).getPinId();
                }
                if (!needMore) {
                    adapter.deleteAll();
                }
                adapter.addAll(bean.getPins());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                refreshLayout.setRefreshing(false);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("X-Requested-With", "XMLHttpRequest");
                return header;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}
