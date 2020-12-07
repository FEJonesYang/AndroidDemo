package com.example.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author JonesYang
 * @Data 2020-12-07
 * @Function 动态广播的接收者
 */
public class DynamicBroad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("DynamicBroad...", "收到广播!");
    }
}
