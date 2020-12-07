package com.example.sharedpreferences;

import java.io.Serializable;

/**
 * @author JonesYang
 * @Data 2020-12-07
 * @Function
 */
public class Product implements Serializable {
    private String Id;
    private String name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}
