package com.example.mvpdemo.presenter;


import android.util.Log;

import androidx.fragment.app.FragmentManager;

import com.example.mvpdemo.interfaces.LoadTaskCallback;
import com.example.mvpdemo.interfaces.NetTask;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author JonesYang
 * @Data 2020-11-23
 * @Function
 */
public class IpInfoTask implements NetTask<String> {

    private static final String HOST = "http://ip.taobao.com/service/getIpInfo.php";
    private static final String TAG = "IpInfoTask";

    /**
     * 静态内部类实现单例模式
     */
    private IpInfoTask() {
    }

    private static class IpInfoTaskHolder {
        private static final IpInfoTask instance = new IpInfoTask();
    }

    public static IpInfoTask getInstance() {
        return IpInfoTaskHolder.instance;
    }


    /**
     * 获取网络数据
     *
     * @param data
     * @param callback
     */
    @Override
    public void execute(String data, final LoadTaskCallback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(HOST).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "获取失败...");
            }


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                callback.onSuccess(response);
            }
        });

    }


}
