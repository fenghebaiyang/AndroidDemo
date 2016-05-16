package com.main.androiddemo.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ManagerRequest {

    private volatile static RequestQueue ourInstance;

    public static RequestQueue getVolleyRequestQueue(Context mContext) {
        if (ourInstance == null) {
            synchronized (ManagerRequest.class) {
                if (ourInstance == null) {
                    ourInstance = Volley.newRequestQueue(mContext.getApplicationContext());
                }
            }
        }
        return ourInstance;
    }
}
