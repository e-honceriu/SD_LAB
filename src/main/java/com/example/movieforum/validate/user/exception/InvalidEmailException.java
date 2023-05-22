package com.example.movieforum.validate.user.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidEmailException extends ValidationException {

    public InvalidEmailException(String error) {
        super(error);
    }
}
