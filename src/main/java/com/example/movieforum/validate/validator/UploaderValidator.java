package com.example.movieforum.validate.validator;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.entity.Role;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.user.exception.UserNotUploaderException;

public class UploaderValidator implements Validator<Movie> {
    @Override
    public void validate(Movie movie) throws ValidationException {
        if(movie.getUploader().getRole() != Role.UPLOADER) {
            throw new UserNotUploaderException("User " + movie.getUploader().getUsername() + " not an uploader");
        }
    }
}
