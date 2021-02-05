package com.example.viewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.viewdemo.interfaces.GitHubService;
import com.example.viewdemo.model.ResponseResult;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitActivity extends AppCompatActivity {

    private static final String uri = "https://wanandroid.com/wxarticle/chapters/json/";
    private static final String TAG = "RetrofitActivity";
    OkHttpActivity mOkHttpActivity = new OkHttpActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query();
                try {
                    mOkHttpActivity.sendRequest(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void query() {


        // 创建 retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(uri)
                .build();

        // 创建访问 api 的请求
        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResponseResult> call = service.getResult();

        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                Log.d(TAG, response.body().getErrorMsg());

                //Log.d(TAG, response.body().getData().toString());
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                Log.d(TAG, "解析错误");
                Log.d(TAG, t.getMessage());
            }
        });

    }
}