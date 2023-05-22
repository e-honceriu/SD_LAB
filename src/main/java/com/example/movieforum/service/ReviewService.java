package com.example.movieforum.service;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.Movie;
import com.example.movieforum.entity.Review;
import com.example.movieforum.exception.*;
import com.example.movieforum.repository.AppUserRepository;
import com.example.movieforum.repository.MovieRepository;
import com.example.movieforum.repository.ReviewRepository;
import com.example.movieforum.validate.review.validator.ReviewValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final AppUserRepository appUserRepository;
    private final ReviewValidator reviewValidator;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, AppUserRepository appUserRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.appUserRepository = appUserRepository;
        this.reviewValidator = new ReviewValidator();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public List<Review> getReviewsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        return reviewRepository.findReviewsByMovie(movie)
                .orElseThrow(() -> new ReviewNotFoundException(movie));
    }

    public List<Review> getReviewsByUser(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return reviewRepository.findReviewsByReviewer(user)
                .orElseThrow(() -> new ReviewNotFoundException(user));
    }

    public Review saveReview(Review review) throws ReviewNotFoundException {
        reviewValidator.validate(review);
        Optional<Review> checkReview = reviewRepository.findReviewByReviewerAndMovie(review.getReviewer(), review.getMovie());
        if (checkReview.isPresent()) {
            throw new ReviewAlreadyPostedException(review.getReviewer().getId(), review.getMovie().getId());
        }
        return reviewRepository.save(review);
    }

    public Review saveReview(Long movie_id, Review review) throws ReviewNotFoundException {
        reviewValidator.validate(review);
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview) throws ReviewNotFoundException {
        Review review = getReviewById(id);
        if (!review.getReviewer().getId().equals(updatedReview.getReviewer().getId())) {
            throw new UserNotReviewOwnerException(review.getReviewer(), review);
        }
        if (updatedReview.getDescription() != null) {
            review.setDescription(updatedReview.getDescription());
        }
        if (updatedReview.getRating() != null) {
            review.setRating(updatedReview.getRating());
        }
        reviewValidator.validate(review);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id, AppUser currentUser) {
        Review review = getReviewById(id);
        if (!review.getReviewer().getId().equals(currentUser.getId())) {
            throw new UserNotReviewOwnerException(currentUser, review);
        }
        reviewRepository.deleteById(id);

    }
}

