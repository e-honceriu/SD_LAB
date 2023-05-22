package com.example.movieforum.validate.user.validator;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.Validator;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements Validator<AppUser> {

    private final List<Validator<AppUser>> validators;

    public UserValidator(){
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new JoinDateValidator());
        validators.add(new NameValidator());
        validators.add(new RoleValidator());
    }
    @Override
    public void validate(AppUser appUser) throws ValidationException {
        for(Validator<AppUser> validator : this.validators) {
            validator.validate(appUser);
        }
    }
}
