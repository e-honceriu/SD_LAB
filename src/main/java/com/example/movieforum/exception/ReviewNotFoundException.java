package com.example.movieforum.exception;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.Movie;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(Long id) {
        super("Review with id=" + id + " not found");
    }

    public ReviewNotFoundException(Movie movie) {
        super("Reviews were not found for movie with id=" + movie.getId());
    }

    public ReviewNotFoundException(AppUser user) {
        super("Reviews were not found for user with id=" + user.getId());
    }
}
