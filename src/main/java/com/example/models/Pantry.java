package com.example.models;

public class Pantry {
    private int usrUUID;
    private String name;
    private int quantity;
    public Pantry(int usrUUID,String name, int quantity){
        this.name=name;
        this.quantity=quantity;
        this.usrUUID=usrUUID;
    }

    public String getName() {
        return name;
    }

    public int getUsrUUID() {
        return usrUUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUsrUUID(int usrUUID) {
        this.usrUUID = usrUUID;
    }

}
