package com.luxuriem.components;

import com.google.common.base.MoreObjects;

import javax.inject.Named;

@Named("component")
public class MyComponent {

    private final String name;

    public MyComponent() {
        this.name = "Juanjo";
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
