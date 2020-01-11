package com.jimei.somato.utils;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.main.androiddemo.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogUtil {

    private static String TAG = "LogUtil";
    private static boolean DEBUG = BuildConfig.DEBUG;

    private static final int ELEMENTS_INDEX = 1;
    static String className;
    static String methodName;
    static int lineNumber;

    private LogUtil() {
        /* Protect from instantiations */
    }

    public static void d(String tag, String arg) {
        if (isEnable()) {
            log(tag, arg);
        }
    }

    public static void d(String logMsg) {
        if (isEnable()) {
            getMethodNames(new Throwable().getStackTrace());
            log(className, createLog(logMsg));
        }
    }

    public static void dd(String tag, Object source) {
        if (isEnable()) {
            getMethodNames(new Throwable().getStackTrace());
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

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[ELEMENTS_INDEX].getFileName();
        methodName = sElements[ELEMENTS_INDEX].getMethodName();
        lineNumber = sElements[ELEMENTS_INDEX].getLineNumber();
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

    private static final char DOUBLE_SIGNER = '═';
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";

    private static String getSplitter(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(DOUBLE_SIGNER);
        }
        return builder.toString();
    }

    private static void format(String tag, Object source) {
        tag = " " + tag + " ";
        log(" ", getSplitter(30) + tag + getSplitter(30));
        log(" ", createLog(className));
        log(" ", SINGLE_DIVIDER);
        log(" ", "" + source);
        log(" ", getSplitter(60 + tag.length()));
        log(" ", " ");
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
        LogUtil.DEBUG = flag;
    }
}