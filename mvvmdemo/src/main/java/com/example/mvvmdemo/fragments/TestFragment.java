package com.example.mvvmdemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.mvvmdemo.R;

/**
 * @author JonesYang
 * @Data 2021-01-23
 * @Function DataBinding 在Fragment 的使用
 */
public class TestFragment extends Fragment {

    private ViewDataBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建 DataBinding 对象
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        //通过 DataBinding 对象创建 View 对象
        View view = mBinding.getRoot();
        //返回 View 对象实例
        return view;
    }
}
