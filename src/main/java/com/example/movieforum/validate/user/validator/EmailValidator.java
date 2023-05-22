package com.example.movieforum.validate.user.validator;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<AppUser> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public void validate(AppUser user) throws ValidationException {
        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new ValidationException("The provided email is not valid");
        }
    }

}
