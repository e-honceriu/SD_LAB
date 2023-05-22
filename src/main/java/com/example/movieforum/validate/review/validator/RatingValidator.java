package com.example.movieforum.validate.review.validator;

import com.example.movieforum.entity.Review;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.review.exception.InvalidRatingException;

public class RatingValidator implements Validator<Review> {
    @Override
    public void validate(Review review) throws ValidationException {
        if(review.getRating() == null) {
            throw new InvalidRatingException("A rating must be provided");
        }
        if(review.getRating() < 0 || review.getRating() > 5) {
            throw new InvalidRatingException("The rating must be between 0 and 5");
        }
    }
}
