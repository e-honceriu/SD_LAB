package com.example.movieforum.exception;

public class UserNotReviewerException extends RuntimeException {

    public UserNotReviewerException(Long id) {
        super("User with id=" + id + " is not an reviewer");
    }
}
