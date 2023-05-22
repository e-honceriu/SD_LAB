package com.example.movieforum.validate.user.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidNameException extends ValidationException {
    public InvalidNameException(String error) {
        super(error);
    }
}
