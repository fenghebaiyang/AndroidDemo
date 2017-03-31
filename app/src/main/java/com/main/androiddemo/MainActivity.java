package com.main.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.main.androiddemo.activity.BehaviorTestActivity;
import com.main.androiddemo.activity.RxTestActivity;
import com.main.androiddemo.adapter.BaseXAdapter;
import com.main.androiddemo.api.GsonGetRequest;
import com.main.androiddemo.bean.HuaBanBean;
import com.main.androiddemo.utils.Logger;
import com.main.androiddemo.utils.StepLineManager;
import com.main.androiddemo.widget.GridImagesDisplay;
import com.main.androiddemo.widget.ProportionImageView;
import com.main.androiddemo.widget.loadmore.LoadMoreContainer;
import com.main.androiddemo.widget.loadmore.LoadMoreHandler;
import com.main.androiddemo.widget.loadmore.LoadMoreListViewContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private GridImagesDisplay dis_1;
    private GridImagesDisplay dis_2;
    private GridImagesDisplay dis_3;
    private GridImagesDisplay dis_4;
    private GridImagesDisplay dis_5;
    private LinearLayout testLayout;

    private ListView mListView;
    private int maxPin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dis_1 = (GridImagesDisplay) findViewById(R.id.dis_1);
        dis_2 = (GridImagesDisplay) findViewById(R.id.dis_2);
        dis_3 = (GridImagesDisplay) findViewById(R.id.dis_3);
        dis_4 = (GridImagesDisplay) findViewById(R.id.dis_4);
        dis_5 = (GridImagesDisplay) findViewById(R.id.dis_5);

        testLayout = (LinearLayout) findViewById(R.id.test_layout);


        testLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> son = new Gson().fromJson(easyString, mapType);

                try {
                    Map<String,Object> map = jsonToMap(new JSONObject(easyString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                /*WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.x = Integer.MAX_VALUE;
                ((WindowManager) getSystemService(WINDOW_SERVICE)).addView(new View(MainActivity.this), layoutParams);*/
            }
        });
        Log.d(TAG, "onCreate: http://huaban.com/partner/uc/aimeinv/pins/");
        Logger.d(TAG, "onCreate: http://huaban.com/partner/uc/aimeinv/pins/");

        // load more container
        final LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_container);
        loadMoreListViewContainer.useDefaultFooter();

        mListView = (ListView) findViewById(R.id.load_more_small_image_list_view);
        final BaseXAdapter adapter = new BaseXAdapter<String>(MainActivity.this) {
            @Override
            public int getConvertViewRes(int position, int type) {
                return R.layout.item_grid_images_display;
            }

            @Override
            public View getItemView(int position, View convertView, ViewGroup parent, ViewHolder viewHolder) {
                ProportionImageView imageView = viewHolder.getView(R.id.img);
                Glide.with(MainActivity.this).load(list.get(position)).into(imageView);
                return convertView;
            }
        };
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StepLineManager.getInstance().addStep(RxTestActivity.class, 1)
                        .addStep(BehaviorTestActivity.class, 2)
                        .addStep(BehaviorTestActivity.class, 3)
                        .addStep(BehaviorTestActivity.class, 4)
                        .addStep(MainActivity.class,5);
                StepLineManager.getInstance().nextStep(MainActivity.this,null);
            }
        });
        getData(loadMoreListViewContainer, adapter);
        /*GsonGetRequest<HuaBanBean> stringRe = new GsonGetRequest<HuaBanBean>("http://huaban.com/partner/uc/aimeinv/pins/", HuaBanBean.class, new Response.Listener<HuaBanBean>() {
            @Override
            public void onResponse(HuaBanBean response) {
                HuaBanBean bean = response;
                List<HuaBanBean.PinsEntity> pins = bean.getPins();
                for (int i = 0; i < pins.size(); i++) {
                    ImageView imageView = new ImageView(MainActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    testLayout.addView(imageView, layoutParams);
                    Glide.with(MainActivity.this).load("http://img.hb.aicdn.com/" + pins.get(i).getFile().getKey()).into(imageView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> head = new HashMap<String, String>();
                head.put("X-Requested-With", "XMLHttpRequest");
                return head;
            }
        };
        ManagerRequest.getVolleyRequestQueue(MainActivity.this).add(stringRe);*/

        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                getData(loadMoreListViewContainer, adapter);
            }
        });
    }

    private void getData(final LoadMoreListViewContainer loadMoreListViewContainer, final BaseXAdapter adapter) {
        String url = "http://huaban.com/partner/uc/aimeinv/pins/";
        if (maxPin > 0) {
            //加载更多
            url += "?max=" + maxPin;
        }
        new GsonGetRequest<HuaBanBean>(MainActivity.this, false, url,
                new Response.Listener<HuaBanBean>() {
                    @Override
                    public void onResponse(HuaBanBean response) {
                        HuaBanBean bean = response;
                        List<HuaBanBean.PinsEntity> pins = bean.getPins();
                        ArrayList<String> urls = new ArrayList<String>();
                        for (int i = 0; i < pins.size(); i++) {
                            urls.add("http://img.hb.aicdn.com/" + pins.get(i).getFile().getKey());
//                    ImageView imageView = new ImageView(MainActivity.this);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    testLayout.addView(imageView, layoutParams);
//                    Glide.with(MainActivity.this).load("http://img.hb.aicdn.com/" + pins.get(i).getFile().getKey()).into(imageView);
                        }

                        dis_1.setImageList(new ArrayList<String>(urls.subList(0, 1)));
                        dis_2.setImageList(new ArrayList<String>(urls.subList(1, 3)));
                        dis_3.setImageList(new ArrayList<String>(urls.subList(3, 6)));
                        dis_4.setImageList(new ArrayList<String>(urls.subList(6, 10)));
                        dis_5.setImageList(new ArrayList<String>(urls.subList(10, urls.size())));

                        if (bean.getPins().size() > 0) {
                            maxPin = bean.getPins().get(bean.getPins().size() - 1).getPinId();
                        }

                        adapter.addAll(urls);

                        loadMoreListViewContainer.loadMoreFinish(false, true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> head = new HashMap<String, String>();
                head.put("X-Requested-With", "XMLHttpRequest");
                return head;
            }
        }.start();

//        Biz.getDemo(MainActivity.this,maxPin, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }, new Response.Listener<HuaBanBean>() {
//            @Override
//            public void onResponse(HuaBanBean response) {
//                HuaBanBean bean = response;
//                List<HuaBanBean.PinsEntity> pins = bean.getPins();
//                ArrayList<String> urls = new ArrayList<String>();
//                for (int i = 0; i < pins.size(); i++) {
//                    urls.add("http://img.hb.aicdn.com/" + pins.get(i).getFile().getKey());
////                    ImageView imageView = new ImageView(MainActivity.this);
////                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
////                    testLayout.addView(imageView, layoutParams);
////                    Glide.with(MainActivity.this).load("http://img.hb.aicdn.com/" + pins.get(i).getFile().getKey()).into(imageView);
//                }
//
//                dis_1.setImageList(new ArrayList<String>(urls.subList(0, 1)));
//                dis_2.setImageList(new ArrayList<String>(urls.subList(1, 3)));
//                dis_3.setImageList(new ArrayList<String>(urls.subList(3, 6)));
//                dis_4.setImageList(new ArrayList<String>(urls.subList(6, 10)));
//                dis_5.setImageList(new ArrayList<String>(urls.subList(10, urls.size())));
//
//                if (bean.getPins().size() > 0) {
//                    maxPin = bean.getPins().get(bean.getPins().size() - 1).getPinId();
//                }
//
//                adapter.addAll(urls);
//
//                loadMoreListViewContainer.loadMoreFinish(false, true);
//            }
//        });
    }

    private ArrayList<String> getImageList(String s) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("img[\\s]src=\"[^(\\s|?)]+\"");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String a = m.group();
            list.add(a.substring(9, a.length() - 1));
        }
        return list;
    }

}
