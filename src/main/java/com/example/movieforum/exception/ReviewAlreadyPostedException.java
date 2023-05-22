package com.example.movieforum.exception;

public class ReviewAlreadyPostedException extends RuntimeException{

    public ReviewAlreadyPostedException(Long userId, Long movieId) {
        super("User with id=" + userId + " already posted a review for movie with id=" + movieId);
    }
}
