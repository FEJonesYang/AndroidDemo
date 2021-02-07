package com.example.viewdemo.dagger.inject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viewdemo.R;

public class SimpleActivity extends AppCompatActivity {

    private SimpleComponent mSimpleComponent;
    private SimpleModule mSimpleModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        mSimpleComponent = DaggerSimpleComponent.builder().simpleModule(getModule()).build();
        mSimpleComponent.inject(this);
    }

    private SimpleModule getModule() {
        return null;
    }
}