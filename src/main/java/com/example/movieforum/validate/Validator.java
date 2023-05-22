package com.example.movieforum.validate;

public interface Validator <T>{

    void validate(T t) throws ValidationException;
}
