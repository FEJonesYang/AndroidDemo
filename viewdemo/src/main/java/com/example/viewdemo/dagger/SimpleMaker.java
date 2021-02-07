package com.example.viewdemo.dagger;

import javax.inject.Inject;

/**
 * @author JonesYang
 * @Data 2021-02-05
 * @Function
 */
public class SimpleMaker implements CoffeeMaker {

    Cooker mCooker;

    @Inject
    public SimpleMaker(Cooker cooker) {
        this.mCooker = cooker;
    }

    @Override
    public String makeCoffee() {
        return mCooker.make();
    }
}
