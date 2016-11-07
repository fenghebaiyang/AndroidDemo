package com.main.androiddemo.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * <br/> Description:文件下载
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/9/21 0021
 */
public class FileDownUtil {

    private String url;
    private String target;
    private DownListener callback;
    private AsyncTask<String, Integer, String> workTask;
    private Context context;

    public interface DownListener {
        void onStart();

        void onLoading(long total, long current);

        void onSuccess(File file);

        void onFailure(String message);
    }

    public FileDownUtil(@NonNull Context context, @NonNull String url, @NonNull String target, @NonNull DownListener callback) {
        this.context = context;
        this.url = url;
        this.target = target;
        this.callback = callback;
    }

    public void start() {
        if (workTask != null) {
            workTask.cancel(true);
        }

        String tempPath = getDiskCacheDir(context, "") + target.substring(target.lastIndexOf(File.separator) + 1, target.lastIndexOf(".")) + ".temp";
        Logger.d(tempPath);

        File tempFile = new File(tempPath);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        workTask = new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                if (callback != null) {
                    callback.onStart();
                }
            }


            @Override
            protected void onProgressUpdate(Integer... values) {
                int code = 0;
                int total = 0;
                int current = 0;
                if (values.length > 2) {
                    code = values[0];
                    total = values[1];
                    current = values[2];
                } else if (values.length > 1) {
                    code = values[0];
                }

                if (code == HTTP_OK) {
                    if (callback != null)
                        callback.onLoading(total, current);
                } else {
                    if (callback != null)
                        callback.onFailure("net error!");
                }
            }

            @Override
            protected void onPostExecute(String s) {
                if (callback != null && !TextUtils.isEmpty(s) && new File(s).exists() && new File(s).length() > 0) {
                    callback.onSuccess(new File(s));
                } else if (callback != null) {
                    callback.onFailure("File not Found!");
                }
            }

            @Override
            protected void onCancelled(String s) {

            }

            @Override
            protected void onCancelled() {

            }

            @Override
            protected String doInBackground(String... params) {
                //初始赋值
                int count = params.length;
                String netPath = null;
                String savePath = null;
                String tempPath = null;

                if (count > 2) {
                    netPath = params[0];
                    savePath = params[1];
                    tempPath = params[2];
                }

                InputStream input = null;
                OutputStream output = null;
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(netPath);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    //获取内容长度
                    int contentLength = conn.getContentLength();
                    int code = conn.getResponseCode();

                    if (code == HTTP_OK) {
                        input = conn.getInputStream();
                        new File(tempPath).createNewFile(); // 创建临时存储文件
                        output = new FileOutputStream(tempPath);

                        byte buffer[] = new byte[1024 * 100];
                        int read = 0;
                        int totalRead = 0;
                        while ((read = input.read(buffer)) != -1) { // 读取信息循环写入文件
                            output.write(buffer, 0, read);
                            totalRead += read;
                            if (totalRead < contentLength) {
                                publishProgress(code, contentLength, totalRead);
                            }
                        }
                        output.flush();
                        new File(tempPath).renameTo(new File(savePath));
                        publishProgress(contentLength, totalRead);
                        return savePath;
                    } else {
                        publishProgress(code);
                    }
                } catch (MalformedURLException e) {
                    publishProgress(-1);
                    e.printStackTrace();
                } catch (IOException e) {
                    publishProgress(-1);
                    e.printStackTrace();
                } finally {
                    try {
                        output.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        input.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        conn.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        workTask.execute(url, target, tempFile.getPath());
    }


    public static String getDiskCacheDir(Context context, String dirName) {
        String cachePath = null;
        File cacheDir;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            cacheDir = context.getExternalCacheDir();
            if (cacheDir != null) {
                cachePath = cacheDir.getPath();
            }
        }

        if (cachePath == null) {
            cacheDir = context.getCacheDir();
            if (cacheDir != null && cacheDir.exists()) {
                cachePath = cacheDir.getPath();
            }
        }

        return cachePath + File.separator + dirName;
    }

    public static String getDiskFilesDir(Context context, String dirName) {
        String filePath = null;
        File fileDir;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            fileDir = context.getExternalFilesDir(null);
            if (fileDir != null) {
                filePath = fileDir.getPath();
            }
        }

        if (filePath == null) {
            fileDir = context.getFilesDir();
            if (fileDir != null && fileDir.exists()) {
                filePath = fileDir.getPath();
            }
        }

        return filePath + File.separator + dirName;
    }
}
