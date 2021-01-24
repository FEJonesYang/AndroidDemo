package com.example.mvvmdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemo.R;

/**
 * @author JonesYang
 * @Data 2021-01-23
 * @Function
 */
public class DMEMAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //DataBinding 在 RecyclerView 的使用
        NewViewHolder newViewHolder = (NewViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
