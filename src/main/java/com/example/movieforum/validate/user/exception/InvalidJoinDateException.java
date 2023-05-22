package com.example.movieforum.validate.user.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidJoinDateException extends ValidationException {
    public InvalidJoinDateException(String error) {
        super(error);
    }
}
