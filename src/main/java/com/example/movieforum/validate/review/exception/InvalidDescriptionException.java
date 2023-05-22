package com.example.movieforum.validate.review.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidDescriptionException extends ValidationException {

    public InvalidDescriptionException(String error) {
        super(error);
    }
}
