package com.example.movieforum.validate.user.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidRoleException extends ValidationException {

    public InvalidRoleException(String error) {
        super(error);
    }
}
