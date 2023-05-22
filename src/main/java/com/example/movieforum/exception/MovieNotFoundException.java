package com.example.movieforum.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super("Movie with id=" + id + " was not found");
    }
}
