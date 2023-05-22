package com.example.movieforum.repository;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.Movie;
import com.example.movieforum.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findReviewsByMovie(Movie movie);

    Optional<List<Review>> findReviewsByReviewer(AppUser user);

    Optional<Review> findReviewByReviewerAndMovie(AppUser user, Movie movie);
}