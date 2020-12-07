package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.demo.broadcast.DynamicBroad;


public class BroadcastActivity extends AppCompatActivity {

    private static final String DynamicBroadNews = "android.net.conn.CONNECTIVITY_CHANGE";
    private DynamicBroad mDynamicBroad;
    private Button mDb_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        mDb_button = findViewById(R.id.dynamic_button);

    }

    private void initEvent() {
        //动态注册
        mDb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity", "开始动态注册...");
                Intent intent = new Intent();
                intent.setAction(DynamicBroadNews);
                sendBroadcast(intent);
            }
        });
    }

    /**
     * 在 onResume() 方法中进行动态注册
     */
    @Override
    protected void onResume() {
        super.onResume();
        mDynamicBroad = new DynamicBroad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DynamicBroadNews);
        registerReceiver(mDynamicBroad, intentFilter);
    }

    /**
     * 在 onPause() 方法中取消动态注册
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mDynamicBroad);
    }
}