package com.example.viewdemo.dagger.inject;

import dagger.Component;

/**
 * @author JonesYang
 * @Data 2021-02-06
 * @Function
 */
@Component(modules = SimpleModule.class)
public interface SimpleComponent {
    void inject(SimpleActivity simpleActivity);
}
