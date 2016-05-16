package com.main.androiddemo.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by justme on 16/1/23.
 */
public class ManagerRequest {

    private volatile static RequestQueue ourInstance;

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link com.android.volley.RequestQueue}
     */
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
