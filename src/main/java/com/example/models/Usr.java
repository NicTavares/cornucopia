package com.example.models;

import java.sql.Date;

public class Usr {
    private  int UUID;
    private  Date birthday;
    private  String email;
    private  String username;
    private  String name;
    private  String password;
    private  String city;
    private  String postalCode;

    public Usr(int UUID,
                Date birthday,
                String email,
                String username,
                String name,
                String password,
                String city,
                String postalCode) {

        this.UUID = UUID;
        this.birthday = birthday;
        this.email = email;
        this.username = username;
        this.name = name;
        this.password = password;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getUUID() {
        return UUID;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setUUID(int UUID){
        this.UUID=UUID;
    }

}