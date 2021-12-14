package com.example.demo.model;

import org.springframework.stereotype.Repository;

@Repository
public class User {
    private String username;
    private String Password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

