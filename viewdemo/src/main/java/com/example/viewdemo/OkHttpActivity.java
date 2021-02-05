package com.example.viewdemo;

import android.util.Log;

import com.example.viewdemo.model.ResponseResult;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author JonesYang
 * @Data 2021-02-06
 * @Function
 */
public class OkHttpActivity {


    private static final String TAG = "RetrofitActivity";

    /**
     * OkHttp 解析数据 异步
     *
     * @return
     * @throws IOException
     */
    public void sendRequest(String uri) throws IOException {

        Request request = new Request.Builder()
                .url(uri)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Gson gson = new Gson();
                ResponseResult responseResult = gson.fromJson(response.body().string(), ResponseResult.class);
                Log.d(TAG, responseResult.getData().toString());
                //Log.d(TAG, res);
            }
        });

    }
}
