package com.example.movieforum.validate.user.validator;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.validate.Validator;
import com.example.movieforum.validate.user.exception.InvalidRoleException;
import com.example.movieforum.validate.ValidationException;

public class RoleValidator implements Validator<AppUser> {
    @Override
    public void validate(AppUser user) throws ValidationException {
        if (user.getRole() == null) {
            throw new InvalidRoleException("No role was provided");
        }
    }
}
