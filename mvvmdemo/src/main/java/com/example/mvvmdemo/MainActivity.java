package com.example.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewDataBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DataBinding 在 Activity 中的使用
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}