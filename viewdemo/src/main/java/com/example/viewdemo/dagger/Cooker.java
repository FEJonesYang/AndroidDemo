package com.example.viewdemo.dagger;

/**
 * @author JonesYang
 * @Data 2021-02-05
 * @Function
 */
public class Cooker {

    String name;

    String coffeeKind;

    public Cooker(String name, String coffeeKind) {
        this.name = name;
        this.coffeeKind = coffeeKind;
    }

    public String make() {
        return name + " make " + coffeeKind;
    }
}
