package com.example.movieforum.validate.review.validator;

import com.example.movieforum.entity.Review;
import com.example.movieforum.entity.Role;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.review.exception.UserNotReviewerException;


public class ReviewerValidator implements Validator<Review> {
    @Override
    public void validate(Review review) throws ValidationException {
        if (review.getReviewer().getRole() != Role.REVIEWER) {
            throw new UserNotReviewerException("User " + review.getReviewer().getUsername() + " not an reviewer");
        }
    }
}
