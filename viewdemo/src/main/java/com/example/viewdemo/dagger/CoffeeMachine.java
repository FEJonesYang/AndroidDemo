package com.example.viewdemo.dagger;


import javax.inject.Inject;

/**
 * @author JonesYang
 * @Data 2021-02-05
 * @Function
 */

public class CoffeeMachine {

    private CoffeeMaker mMaker;

    @Inject
    public CoffeeMachine(Cooker cooker) {
        mMaker = new SimpleMaker(cooker);
    }

    public String makeCoffee() {
        return mMaker.makeCoffee();
    }

}


