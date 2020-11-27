package com.example.mvpdemo.interfaces;

/**
 * @author JonesYang
 * @Data 2020-11-23
 * @Function
 */
public interface NetTask<T> {
    void execute(T data, LoadTaskCallback callback);
}
