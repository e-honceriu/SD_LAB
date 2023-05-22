package com.example.movieforum.validate.user.validator;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.user.exception.InvalidNameException;
import com.example.movieforum.validate.ValidationException;

public class NameValidator implements Validator<AppUser> {


    @Override
    public void validate(AppUser user) throws ValidationException {
        if ((user.getFirstname() == null) || (user.getFirstname().length() == 0) || (user.getFirstname().length() > 50)) {
            throw new InvalidNameException("Invalid firstname provided, it should have at most 50 characters");
        }
        if ((user.getLastname() == null) || (user.getLastname().length() == 0) || (user.getLastname().length() > 50)) {
            throw new InvalidNameException("Invalid lastname provided, it should have at most 50 characters");
        }
    }
}
