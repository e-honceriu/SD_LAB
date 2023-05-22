package com.example.movieforum.validate.review.exception;

import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;

public class UserNotReviewerException extends ValidationException {
    public UserNotReviewerException(String error) {
        super(error);
    }
}
