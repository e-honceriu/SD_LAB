package com.example.movieforum.validate.user.validator;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.user.exception.InvalidJoinDateException;
import com.example.movieforum.validate.ValidationException;

import java.time.LocalDate;

public class JoinDateValidator implements Validator<AppUser> {

    @Override
    public void validate(AppUser user) throws ValidationException {
        if (user.getJoinDate().isAfter(LocalDate.now())) {
            throw new InvalidJoinDateException("Invalid join date, cannot join from the future");
        }
    }
}
