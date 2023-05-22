package com.example.movieforum.entity;

public class LogInDetails {

    private final String username;
    private final String password;

    public LogInDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}