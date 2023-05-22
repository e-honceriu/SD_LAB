package com.example.movieforum.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String username) {
        super("Invalid password provided for user with username"+username);
    }
}
