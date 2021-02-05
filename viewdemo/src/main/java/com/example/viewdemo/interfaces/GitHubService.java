package com.example.viewdemo.interfaces;

import com.example.viewdemo.model.ResponseResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


/**
 * @author JonesYang
 * @Data 2021-02-05
 * @Function
 */
public interface GitHubService {
    @GET("https://wanandroid.com/wxarticle/chapters/json")
    Call<ResponseResult> getResult();
}
