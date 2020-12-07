package com.example.demo;

import android.content.Context;
import android.os.Handler;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author JonesYang
 * @Data 2020-11-30
 * @Function 实现 OkHttp 的简单封装
 */
public class OkHttpManager {
    // 单例 OkHttpManager
    private static volatile OkHttpManager sOkHttpManager;
    // 更新 UI 必须使用 Handler
    private Handler mHandler;
    // OkHttpClient
    private OkHttpClient mOkHttpClient;

    /**
     * 私有化构造函数，使得外部类不能访问。
     * 定义 缓存、超时时间、OkHttpClient等
     *
     * @param context
     */
    private OkHttpManager(Context context) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        // 通过 OkHttpClient.Builder 实例化 OkHttpClient
        mOkHttpClient = builder.build();
        mHandler = new Handler();
    }

    //Double Check
    public static OkHttpManager getInstance(Context context) {
        if (sOkHttpManager == null) {
            synchronized (OkHttpManager.class) {
                if (sOkHttpManager == null) {
                    sOkHttpManager = new OkHttpManager(context);
                }
            }
        }
        return sOkHttpManager;
    }

    /**
     * 异步的 get 请求
     *
     * @param url
     * @param callback
     */
    public void getAsynHttp(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call, callback);
    }

    /**
     * 获取异步 get 请求
     *
     * @param call
     * @param callback
     */
    private void dealResult(Call call, ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                sendFailedCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                sendSuccessCallback(response.body().string(), callback);
            }
        });
    }

    /**
     * 获取成功，更新UI
     *
     * @param string
     * @param callback
     */
    private void sendSuccessCallback(String string, ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    try {
                        callback.onResponse(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 获取失败
     *
     * @param request
     * @param e
     * @param callback
     */
    private void sendFailedCallback(Request request, IOException e, ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(request, e);
                }
            }
        });
    }


}

interface ResultCallback {
    void onError(Request request, Exception e);

    void onResponse(String s) throws IOException;
}

