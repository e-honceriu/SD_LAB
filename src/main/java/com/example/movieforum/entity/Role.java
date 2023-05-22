package com.example.movieforum.entity;

public enum Role {
    REVIEWER(1),
    UPLOADER(2);

    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
