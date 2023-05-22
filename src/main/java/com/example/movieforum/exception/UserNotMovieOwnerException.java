package com.example.movieforum.exception;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.Movie;

public class UserNotMovieOwnerException extends RuntimeException{

    public UserNotMovieOwnerException(AppUser user, Movie movie) {
        super("User with id="+user.getId()+" is not the owner of the movie with id="+movie.getId());
    }
}
