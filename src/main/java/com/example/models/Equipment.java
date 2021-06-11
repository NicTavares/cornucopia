package com.example.models;

public class Equipment {
    private String name;
    public Equipment()
    {
        super();
    }
    public Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
