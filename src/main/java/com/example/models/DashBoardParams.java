package com.example.models;

public class DashBoardParams {
    private String field;
    public DashBoardParams(){

    }
    public DashBoardParams(String field){
        this.field=field;
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
