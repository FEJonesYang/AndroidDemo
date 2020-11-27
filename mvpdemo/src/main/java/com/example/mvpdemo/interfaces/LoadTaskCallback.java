package com.example.mvpdemo.interfaces;

/**
 * @author JonesYang
 * @Data 2020-11-23
 * @Function
 */
public interface LoadTaskCallback<T> {
    void onSuccess(T t);

    void onStart();

    void onFailed();

    void onFinish();
}
