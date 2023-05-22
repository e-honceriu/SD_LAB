package com.example.movieforum.validate;

public class ValidationException extends RuntimeException{

    public ValidationException(String error) {
        super(error);
    }
}
