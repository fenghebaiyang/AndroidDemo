package com.main.androiddemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Base64;

import com.main.androiddemo.base.App;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <br/> Description:SharePreferences 帮助类
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/10/8 0008
 */
public class SPHelper {
    private Context context;
    private SharedPreferences prefrence;

    public SPHelper() {
        this.context = App.getInstance();
    }

    public SPHelper(Context c) {
        this.context = c.getApplicationContext();
    }

    public SPHelper open(@StringRes int res) {
        return this.open(context.getString(res));
    }

    public SPHelper open(String name) {
        return this.open(name, 0);
    }

    public SPHelper open(@StringRes int res, int version) {
        return this.open(context.getString(res), version);
    }

    public SPHelper open(String name, int version) {
        String fileName = name + "_" + version;
        this.prefrence = this.context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return this;
    }

    public void putString(@StringRes int res, String value) {
        this.putString(context.getString(res), value);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(@StringRes int res) {
        return this.getString(context.getString(res));
    }

    public String getString(String key) {
        return this.prefrence.getString(key, "");
    }

    public void putBoolean(@StringRes int res, Boolean value) {
        this.putBoolean(context.getString(res), value);
    }

    public void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putBoolean(key, value.booleanValue());
        editor.commit();
    }

    public boolean getBoolean(@StringRes int res) {
        return this.getBoolean(context.getString(res));
    }

    public boolean getBoolean(String key) {
        return this.prefrence.getBoolean(key, false);
    }

    public void putLong(@StringRes int res, Long value) {
        this.putLong(context.getString(res), value);
    }

    public void putLong(String key, Long value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putLong(key, value.longValue());
        editor.commit();
    }

    public long getLong(@StringRes int res) {
        return this.getLong(context.getString(res));
    }

    public long getLong(String key) {
        return this.prefrence.getLong(key, 0L);
    }

    public void putInt(@StringRes int res, Integer value) {
        this.putInt(context.getString(res), value);
    }

    public void putInt(String key, Integer value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putInt(key, value.intValue());
        editor.commit();
    }

    public int getInt(@StringRes int res) {
        return this.getInt(context.getString(res));
    }

    public int getInt(String key) {
        return this.prefrence.getInt(key, 0);
    }

    public void putFloat(@StringRes int res, Float value) {
        this.putFloat(context.getString(res), value);
    }

    public void putFloat(String key, Float value) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.putFloat(key, value.floatValue());
        editor.commit();
    }

    public float getFloat(@StringRes int res) {
        return this.getFloat(context.getString(res));
    }

    public float getFloat(String key) {
        return this.prefrence.getFloat(key, 0.0F);
    }

    public void put(@StringRes int res, Object value) {
        this.put(context.getString(res), value);
    }

    public void put(String key, Object value) {
        if (value != null) {
            try {
                ByteArrayOutputStream t = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(t);
                oos.writeObject(value);
                oos.flush();
                oos.close();
                byte[] data = t.toByteArray();
                String base64 = Base64.encodeToString(data, 2);
                this.putString(key, base64);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object get(@StringRes int res) {
        return this.get(context.getString(res));
    }

    public Object get(String key) {
        try {
            String t = this.getString(key);
            if (TextUtils.isEmpty(t)) {
                return null;
            } else {
                byte[] data = Base64.decode(t, 2);
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Object value = ois.readObject();
                ois.close();
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(@StringRes int res) {
        this.remove(context.getString(res));
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = this.prefrence.edit();
        editor.remove(key);
        editor.commit();
    }
}
