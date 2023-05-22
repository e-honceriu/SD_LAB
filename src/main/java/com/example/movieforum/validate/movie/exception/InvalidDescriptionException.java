package com.example.movieforum.validate.movie.exception;


import com.example.movieforum.validate.ValidationException;

public class InvalidDescriptionException extends ValidationException {

    public InvalidDescriptionException(String error) {
        super(error);
    }
}
