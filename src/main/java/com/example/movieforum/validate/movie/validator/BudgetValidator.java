package com.example.movieforum.validate.movie.validator;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.movie.exception.InvalidBudgetException;

public class BudgetValidator implements Validator<Movie> {


    @Override
    public void validate(Movie movie) throws ValidationException {
        if (movie.getBudget() == null) {
            throw new InvalidBudgetException("A budget must be provided");
        }
        if (movie.getBudget() < 0) {
            throw new InvalidBudgetException("The budget should be positive");
        }
    }
}
