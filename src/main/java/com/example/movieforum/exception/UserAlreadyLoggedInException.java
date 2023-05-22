package com.example.movieforum.exception;

public class UserAlreadyLoggedInException extends RuntimeException{

    public UserAlreadyLoggedInException() {
        super("A user is already logged in");
    }
}
