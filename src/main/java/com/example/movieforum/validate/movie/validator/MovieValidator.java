package com.example.movieforum.validate.movie.validator;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;

import java.util.ArrayList;
import java.util.List;

public class MovieValidator implements Validator<Movie> {

    private final List<Validator<Movie>> validatorList;

    public MovieValidator() {
        validatorList = new ArrayList<>();
        validatorList.add(new BudgetValidator());
        validatorList.add(new DescriptionValidator());
        validatorList.add(new ReleaseDateValidator());
        validatorList.add(new RuntimeValidator());
        validatorList.add(new TitleValidator());
        validatorList.add(new UploaderValidator());
    }
    @Override
    public void validate(Movie movie) throws ValidationException {
        for(Validator<Movie> validator : validatorList) {
            validator.validate(movie);
        }
    }
}
