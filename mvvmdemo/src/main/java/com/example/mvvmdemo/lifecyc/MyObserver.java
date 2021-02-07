package com.example.mvvmdemo.lifecyc;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author JonesYang
 * @Data 2021-02-07
 * @Function
 */
public class MyObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        Log.d("MyObserver", "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        Log.d("MyObserver", "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void Resume() {
        Log.d("MyObserver", "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void Pause() {
        Log.d("MyObserver", "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        Log.d("MyObserver", "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        Log.d("MyObserver", "onDestroy");
    }


}
