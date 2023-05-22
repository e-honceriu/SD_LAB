package com.example.movieforum.validate.validator;


import com.example.movieforum.entity.Movie;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.movie.exception.InvalidTitleException;

public class TitleValidator implements Validator<Movie> {

    @Override
    public void validate(Movie movie) throws ValidationException {
        if (movie.getTitle() == null) {
            throw new InvalidTitleException("A title must be provided");
        }
        if (movie.getTitle().length() > 50) {
            throw new InvalidTitleException("A title should have a length <= 50");
        }
    }
}
