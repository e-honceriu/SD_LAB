package com.example.movieforum.validate.validator;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.movie.exception.InvalidRuntimeException;

public class RuntimeValidator implements Validator<Movie> {
    @Override
    public void validate(Movie movie) throws ValidationException {
        if (movie.getRuntime() == null) {
            throw new InvalidRuntimeException("A runtime must be provided");
        }
        if (movie.getRuntime() <= 0) {
            throw new InvalidRuntimeException("The runtime must be greater then 0");
        }
    }
}
