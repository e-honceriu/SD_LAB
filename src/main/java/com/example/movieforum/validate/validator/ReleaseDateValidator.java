package com.example.movieforum.validate.validator;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.movie.exception.InvalidReleaseDateException;

public class ReleaseDateValidator implements Validator<Movie> {
    @Override
    public void validate(Movie movie) throws ValidationException {
        if (movie.getReleaseDate() == null) {
            throw new InvalidReleaseDateException("A release date must be provided");
        }
    }
}
