package com.example.movieforum.validate.movie.validator;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.movie.exception.InvalidDescriptionException;

public class DescriptionValidator implements Validator<Movie> {
    @Override
    public void validate(Movie movie) throws ValidationException {
        if (movie.getDescription() == null) {
            throw new InvalidDescriptionException("A description must be provided");
        }
        if (movie.getDescription().length() > 200) {
            throw new InvalidDescriptionException("A description must have maximum 200 characters");
        }
    }
}