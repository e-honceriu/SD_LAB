package com.example.movieforum.validate.movie.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidRuntimeException extends ValidationException {
    public InvalidRuntimeException(String error) {
        super(error);
    }
}
