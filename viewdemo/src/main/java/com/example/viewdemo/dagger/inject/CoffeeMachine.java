package com.example.viewdemo.dagger.inject;


import com.example.viewdemo.dagger.CoffeeMaker;

import javax.inject.Inject;

/**
 * @author JonesYang
 * @Data 2021-02-05
 * @Function
 */

public class CoffeeMachine {

    public CoffeeMaker mMaker;

    @Inject
    public CoffeeMachine(CoffeeMaker maker) {
        this.mMaker = maker;
    }

    public String makeCoffee() {
        return mMaker.makeCoffee();
    }

}


