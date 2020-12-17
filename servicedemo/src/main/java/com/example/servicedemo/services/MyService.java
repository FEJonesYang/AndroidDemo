package com.example.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG","Service create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG","Service start");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("TAG","Service bind");
        return new Binder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("TAG","Service unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("TAG","Service destroy");
        super.onDestroy();
    }
}