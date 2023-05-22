package com.example.movieforum.exception;

public class NoUserLoggedInException extends RuntimeException{

    public NoUserLoggedInException() {
        super("No user is currently logged id");
    }

}
