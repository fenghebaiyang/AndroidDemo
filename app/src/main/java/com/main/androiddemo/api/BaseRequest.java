package com.main.androiddemo.api;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * <br/> Description:基于构造者模式的Request
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/5/17 0017
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public abstract class BaseRequest<T> extends Request<T> {

    public BaseRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }
}
