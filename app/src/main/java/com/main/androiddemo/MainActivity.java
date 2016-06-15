package com.main.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.main.androiddemo.api.Biz;
import com.main.androiddemo.bean.HuaBanBean;
import com.main.androiddemo.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private LinearLayout testLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Biz.getDemo(MainActivity.this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<HuaBanBean>() {
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
        });
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
