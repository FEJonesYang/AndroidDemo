package com.example.mvvmdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author JonesYang
 * @Data 2021-01-24
 * @Function
 */
public class ViewModelActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmodel);
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
    }
}
