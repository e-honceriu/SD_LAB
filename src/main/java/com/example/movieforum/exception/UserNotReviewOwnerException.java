package com.example.movieforum.exception;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.Review;

public class UserNotReviewOwnerException extends RuntimeException{

    public UserNotReviewOwnerException(AppUser user, Review review) {
        super("User with id="+user.getId()+" is not the owner of the review with id="+review.getId());
    }
}