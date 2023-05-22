package com.example.movieforum.validate.movie.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidReleaseDateException extends ValidationException {
    public InvalidReleaseDateException(String error) {
        super(error);
    }
}
