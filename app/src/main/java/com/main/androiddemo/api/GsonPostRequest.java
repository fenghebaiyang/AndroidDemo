package com.main.androiddemo.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.main.androiddemo.utils.Logger;
import com.main.androiddemo.utils.ReflectUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

public class GsonPostRequest<T> extends JsonRequest<T> {

    private Response.Listener<T> listener;
    private Context mContext;
    private ProgressDialog dialog;

    public GsonPostRequest
            (String url, Map<String, String> params,
             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(null, false, url, params, listener, errorListener);
    }

    public GsonPostRequest
            (Context mContext, boolean cancelAble, String url, Map<String, String> params,
             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, HttpHeaderParser.parseCharset(params), listener, errorListener);
        Logger.dd("url", url);
        Logger.dd("params", params);
        this.listener = listener;
        if (mContext != null) {
            this.mContext = mContext;
            dialog = new ProgressDialog(mContext);
            dialog.setCancelable(cancelAble);
            dialog.setCanceledOnTouchOutside(cancelAble);
            if (cancelAble) {
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        cancel();
                    }
                });
            }
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        listener.onResponse(response);
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
    public void deliverError(VolleyError error) {
        if (dialog != null && !dialog.isShowing())
            dialog.dismiss();
        super.deliverError(error);
    }

    public void start() {
        if (mContext == null) return;
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        ManagerRequest.getVolleyRequestQueue(mContext).add(this);
    }
}