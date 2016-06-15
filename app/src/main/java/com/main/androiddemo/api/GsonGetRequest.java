package com.main.androiddemo.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;


public class GsonGetRequest<T> extends Request<T> {
    private Gson gson;
    private Response.Listener<T> listener;
    private Class<T> mClazz;

    private Context mContext;
    private ProgressDialog dialog;

    public GsonGetRequest(String url, Class<T> mClazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(null, false, url, mClazz, listener, errorListener);
    }

    public GsonGetRequest
            (Context mContext, boolean cancelAble, String url, Class<T> mClazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        gson = new Gson();
        this.mClazz = mClazz;
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

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
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