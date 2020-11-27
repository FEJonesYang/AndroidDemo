package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "MainActivity2 create...");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity2 start...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity2 Resume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity2 pause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity2 stop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity2 destroy...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity2 restart...");
    }
}