package com.example.movieforum.validate.movie.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidTitleException extends ValidationException {
    public InvalidTitleException(String error) {
        super(error);
    }
}
