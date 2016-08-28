package com.main.androiddemo.api;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.main.androiddemo.utils.Logger;
import com.main.androiddemo.utils.ReflectUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * <br/> Description:Gson请求类
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/8/28 0028
 */
public class GsonRequest<T> extends Request<T> {

    private Response.Listener<T> mListener = null;

    public GsonRequest(String url, String requestBody, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(Method.DEPRECATED_GET_OR_POST, url, requestBody, listener, errorListener);
    }

    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Logger.dd("result", json);
            Class<T> clazz = null;
            Type type = ReflectUtil.getType(getClass(), 0);
            if (type instanceof Class) {
                clazz = (Class<T>) type;
            }
            return Response.success(new Gson().fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
