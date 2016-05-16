package com.main.androiddemo.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.main.androiddemo.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Logger {

    private static String TAG = "Logger";
    private static boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String arg) {
        if (isEnable()) {
            log(tag, arg);
        }
    }

    public static void d(String logMsg) {
        if (isEnable()) {
            log(getCurrentClassName(), getCurrentMethodName() + "(): " + logMsg);
        }
    }

    public static void dd(String tag, Object source) {
        if (isEnable()) {
            Object o = getJsonObjFromStr(source);
            if (o != null) {
                try {
                    if (o instanceof JSONObject) {
                        format(tag, ((JSONObject) o).toString(2));
                    } else if (o instanceof JSONArray) {
                        format(tag, ((JSONArray) o).toString(2));
                    } else {
                        format(tag, source);
                    }
                } catch (JSONException e) {
                    format(tag, source);
                }
            } else {
                format(tag, source);
            }
        }
    }

    private static final int CHUNK_SIZE = 4000;

    /**
     * <p/>  调用系统日志打印内容
     * <br/> @version 1.0
     * <br/> @createTime 2016/5/16 0016 15:55
     * <br/> @updateTime 2016/5/16 0016 15:55
     * <br/> @createAuthor xiaojianfeng
     * <br/> @updateAuthor xiaojianfeng
     * <br/> @updateInfo (此处输入修改内容,若无修改可不写.)
     *
     * @param tag tag
     * @param msg 内容
     */
    private static void log(String tag, String msg) {
        if (msg.length() <= CHUNK_SIZE) {
            Log.d(TextUtils.isEmpty(tag.trim()) ? TAG : tag, msg);
        } else {
            for (int i = 0; i < msg.length(); i += CHUNK_SIZE) {
                int count = Math.min(msg.length() - i, CHUNK_SIZE);
                Log.d(TextUtils.isEmpty(tag.trim()) ? TAG : tag, msg.substring(i, i + count));
            }
        }
    }

    private static String getSplitter(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("-");
        }
        return builder.toString();
    }

    private static void format(String tag, Object source) {
        tag = " " + tag + " ";
        log(" ", " ");
        log(" ", getSplitter(50) + tag + getSplitter(50));
        log(" ", "" + source);
        log(" ", getSplitter(100 + tag.length()));
        log(" ", " ");
    }

    private static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    private static String getCurrentClassName() {
        String className = Thread.currentThread().getStackTrace()[4].getClassName();
        String[] temp = className.split("[\\.]");
        className = temp[temp.length - 1];
        return className;
    }

    private static Object getJsonObjFromStr(Object test) {
        Object o = null;
        try {
            o = new JSONObject(test.toString());
        } catch (JSONException ex) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    o = new JSONArray(test);
                }
            } catch (JSONException ex1) {
                return null;
            }
        }
        return o;
    }

    public static boolean isEnable() {
        return DEBUG;
    }

    public static void setEnable(boolean flag) {
        Logger.DEBUG = flag;
    }
}