package com.example.models;

public class UsrStatistics {
    private int UUID;
    private String name;
    private int value;

    public UsrStatistics(){
        super();
    }
    public UsrStatistics(int UUID,String name, int value){
        this.name=name;
        this.UUID=UUID;
        this.value=value;
    }
    public int getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }



    public int getValue() {
        return value;
    }
}
