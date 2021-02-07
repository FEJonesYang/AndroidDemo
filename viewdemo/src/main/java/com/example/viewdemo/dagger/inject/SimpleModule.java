package com.example.viewdemo.dagger.inject;

import com.example.viewdemo.dagger.Cooker;

import dagger.Module;
import dagger.Provides;

/**
 * @author JonesYang
 * @Data 2021-02-06
 * @Function
 */
@Module
public class SimpleModule {

    @Provides
    Cooker provideCooker() {
        return new Cooker("James", "Espresso");
    }

}
