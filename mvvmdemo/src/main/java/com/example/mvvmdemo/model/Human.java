package com.example.mvvmdemo.model;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * @author JonesYang
 * @Data 2021-01-23
 * @Function 使用 ObservableField 进行数据单向绑定
 */
public class Human {
    public final ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();

    public Human(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }
}
