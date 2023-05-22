package com.example.movieforum.validate.movie.exception;

import com.example.movieforum.validate.ValidationException;

public class InvalidBudgetException extends ValidationException {
    public InvalidBudgetException(String error) {
        super(error);
    }
}
