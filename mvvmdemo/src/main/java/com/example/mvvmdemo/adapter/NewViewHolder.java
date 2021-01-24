package com.example.mvvmdemo.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author JonesYang
 * @Data 2021-01-23
 * @Function
 */
public class NewViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding mViewDataBinding;

    public NewViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.mViewDataBinding = binding;
    }
}
