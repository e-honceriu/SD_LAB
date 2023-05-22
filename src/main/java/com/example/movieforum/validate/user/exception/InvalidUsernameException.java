package com.example.movieforum.validate.user.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidUsernameException extends ValidationException {

    public InvalidUsernameException(String error) {
        super(error);
    }
}
