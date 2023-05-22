package com.example.movieforum.validate.review.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidRatingException extends ValidationException {
    public InvalidRatingException(String error) {
        super(error);
    }
}
