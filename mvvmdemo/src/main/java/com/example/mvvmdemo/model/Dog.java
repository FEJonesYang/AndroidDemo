package com.example.mvvmdemo.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * @author JonesYang
 * @Data 2021-01-23
 * @Function 使用 BaseObservable 进行单向数据绑定
 */
public class Dog extends BaseObservable {


    //如果是public修饰的，直接用@Bindable
    @Bindable
    public String name;
    //如果是private修饰的，则在get方法使用@Bindable
    private String color;


    public void setDataOnlyName(String name, String color) {
        this.name = name;
        this.color = color;
        //只刷name字段
        notifyPropertyChanged(com.example.mvvmdemo.BR.name);
    }

    public void setDataAll(String name, String color) {
        this.name = name;
        this.color = color;
        //刷新全部字段
        notifyChange();
    }

    @Bindable
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
