package com.main.androiddemo.api;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonPostRequest<T> extends JsonRequest<T> {
    private Gson gson;
    private Response.Listener<T> listener;
    private Class<T> mClazz;

    public GsonPostRequest
            (String url, Map<String, String> body, Class<T> mClazz,
             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, HttpHeaderParser.parseCharset(body), listener, errorListener);
        gson = new Gson();
        this.mClazz = mClazz;
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}