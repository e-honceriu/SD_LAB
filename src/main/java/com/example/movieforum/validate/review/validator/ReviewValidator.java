package com.example.movieforum.validate.review.validator;

import com.example.movieforum.entity.Review;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;

import java.util.ArrayList;
import java.util.List;

public class ReviewValidator implements Validator<Review> {

    private final List<Validator<Review>> validators;

    public ReviewValidator() {
        this.validators = new ArrayList<>();
        this.validators.add(new DescriptionValidator());
        this.validators.add(new RatingValidator());
        this.validators.add(new ReviewerValidator());
    }
    @Override
    public void validate(Review review) throws ValidationException {
        for(Validator<Review> validator : this.validators) {
            validator.validate(review);
        }
    }
}
