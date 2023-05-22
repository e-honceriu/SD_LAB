package com.example.movieforum.validate.user.exception;

import com.example.movieforum.validate.ValidationException;

public class UserNotUploaderException extends ValidationException {
    public UserNotUploaderException(String error) {
        super(error);
    }
}
