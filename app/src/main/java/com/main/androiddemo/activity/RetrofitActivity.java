package com.main.androiddemo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.main.androiddemo.R;
import com.main.androiddemo.api.ServiceInterface;
import com.main.androiddemo.bean.HuaBanBean;
import com.main.androiddemo.utils.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
//                getData(1287405042);
                getDataRx(1287405042);
            }
        });
    }

    @Override
    protected void init() {

    }

    private void getData(int pin) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://huaban.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ServiceInterface sInter = retrofit.create(ServiceInterface.class);
        Call<HuaBanBean> call = sInter.getHuaBanList(pin);
        call.enqueue(new Callback<HuaBanBean>() {
            @Override
            public void onResponse(Call<HuaBanBean> call, Response<HuaBanBean> response) {
                Logger.dd(response.message(), response.body());
                HuaBanBean temp = response.body();
                Logger.dd("HuaBanBean", temp);
            }

            @Override
            public void onFailure(Call<HuaBanBean> call, Throwable t) {
                Logger.dd("Throwable", t);
            }
        });

//        Call<ResponseBody> responseCall = sInter.getHuaBanListssss(pin);
//        responseCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    LogUtils.json(response.message(), response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Logger.dd("Throwable", t);
//            }
//        });
    }

    private CompositeDisposable disposables;

    private void getDataRx(long pin) {
        if (disposables == null) disposables = new CompositeDisposable();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://huaban.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ServiceInterface sInter = retrofit.create(ServiceInterface.class);

        disposables.add(sInter.getHuaBanListOb(pin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HuaBanBean>() {
                    @Override
                    public void accept(HuaBanBean huaBanBean) throws Exception {
                        Logger.dd("HuaBanBean", huaBanBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.dd("Throwable", throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.d("onComm");
                    }
                }));


    }
}
