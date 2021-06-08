package com.example.models;

public class Administrator {
    private int UUID;
    private int adminLevel;
    private String password;
    private String email;

    public Administrator(int UUID,
                         int adminLevel,
                         String password,
                         String email) {
        this.UUID = UUID;
        this.adminLevel = adminLevel;
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getUUID() {
        return UUID;
    }

    public int getAdminLevel() {
        return adminLevel;
    }


    // Admin_UUID: INT, admin_level: INT, password: CHAR, email: CHAR

}
