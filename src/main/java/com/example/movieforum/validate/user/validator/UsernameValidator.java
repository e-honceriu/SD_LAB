package com.example.movieforum.validate.user.validator;

import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.user.exception.InvalidUsernameException;
import com.example.movieforum.validate.ValidationException;

public class UsernameValidator implements Validator<String> {

    @Override
    public void validate(String username) throws ValidationException {
        if ((username == null) || (username.length() < 3) || (username.length() > 20)) {
            throw new InvalidUsernameException("The username should be at least 3 characters long and at most 20");
        }
    }
}
