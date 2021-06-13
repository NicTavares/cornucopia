package com.example.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Usr {

    private  int UUID;
    private  String email;
    private  String username;
    private  String name;
    private  String password;
    private  String city;
    private  String postalCode;

    public Usr() {}

    public Usr(int UUID,
                String email,
                String username,
                String name,
                String password,
                String city,
                String postalCode) {

        this.UUID = UUID;
        this.email = email;
        this.username = username;
        this.name = name;
        this.password = password;
        this.city = city;
        this.postalCode = postalCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    @Override
    public String toString() {
        return "Usr{" +
                "UUID=" + UUID +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}