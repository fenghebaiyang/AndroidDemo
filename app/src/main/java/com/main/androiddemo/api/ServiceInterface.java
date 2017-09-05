package com.main.androiddemo.api;

import com.main.androiddemo.bean.HuaBanBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @Description
 * @Author xiaojianfeng
 * @Date 2017/9/5 0005 14:10
 */

public interface ServiceInterface {
    @Headers("X-Requested-With:XMLHttpRequest")
    @GET("partner/uc/aimeinv/pins/")
    Call<HuaBanBean> getHuaBanList(@Query(value="max", encoded=true) long pin);


    @GET("/")
    Call<ResponseBody> getBaiduUrl();

    @Headers("X-Requested-With:XMLHttpRequest")
    @GET("partner/uc/aimeinv/pins/")
    Call<ResponseBody> getHuaBanListssss(@Query(value="max", encoded=true) long pin);
}
