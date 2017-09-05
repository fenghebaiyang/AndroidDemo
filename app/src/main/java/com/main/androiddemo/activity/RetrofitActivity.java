package com.main.androiddemo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.main.androiddemo.R;
import com.main.androiddemo.api.ServiceInterface;
import com.main.androiddemo.utils.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description
 * @Author xiaojianfeng
 * @Date 2017/9/5 0005 12:45
 */

public class RetrofitActivity extends BaseActivity {
    private Button btnTest;
    private TextView txtResult;

    @Override
    protected void initGetData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.act_retrofit;
    }

    @Override
    protected void findViews() {
        btnTest = (Button) findViewById(R.id.btn_test);
        txtResult = (TextView) findViewById(R.id.txt_result);
    }

    @Override
    protected void widgetListener() {
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(1287405042);
            }
        });
    }

    @Override
    protected void init() {

    }

    private void getData(int pin) {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://huaban.com/partner/uc/aimeinv/pins/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ServiceInterface sInter = retrofit.create(ServiceInterface.class);
//        Call<HuaBanBean> call = sInter.getHuaBanList(pin);
//        call.enqueue(new Callback<HuaBanBean>() {
//            @Override
//            public void onResponse(Call<HuaBanBean> call, Response<HuaBanBean> response) {
//                Logger.dd(response.message(), response.body());
//                HuaBanBean temp = response.body();
//                Logger.dd("HuaBanBean", temp);
//            }
//
//            @Override
//            public void onFailure(Call<HuaBanBean> call, Throwable t) {
//                Logger.dd("Throwable", t);
//            }
//        });

        Call<ResponseBody> responseCall = sInter.getHuaBanListssss(pin);
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LogUtils.json(response.message(), response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.dd("Throwable", t);
            }
        });
    }
}
