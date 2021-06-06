package com.example.models;

import java.sql.Date;

public class User {
    private final int UUID;
    private final Date birthday;
    private final String email;
    private final String name;
    private final String password;
    private final String city;
    private final String postalCode;

    public User(int UUID, Date birthday,
                String email,
                String name,
                String password,
                String city,
                String postalCode) {

        this.UUID = UUID;

        this.birthday = birthday;

        this.email = email;

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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPostalCode() {
        return postalCode;
    }

}