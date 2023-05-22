package com.example.movieforum.validate.review.validator;

import com.example.movieforum.entity.Review;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.review.exception.InvalidDescriptionException;

public class DescriptionValidator implements Validator<Review> {
    @Override
    public void validate(Review review) throws ValidationException {
        if (review.getDescription() == null) {
            throw new InvalidDescriptionException("A description must be provided");
        }
        if (review.getDescription().length() > 200) {
            throw new InvalidDescriptionException("A description must have maximum 200 characters");
        }
    }
}
