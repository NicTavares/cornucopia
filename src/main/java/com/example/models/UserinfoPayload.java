package com.example.models;

public class UserinfoPayload {
    private String username;
    private String table;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "UserinfoPayload{" +
                "username='" + username + '\'' +
                ", table='" + table + '\'' +
                '}';
    }
}
