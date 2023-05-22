package com.example.movieforum.controller;

import com.example.movieforum.entity.Review;
import com.example.movieforum.exception.*;
import com.example.movieforum.service.AuthService;
import com.example.movieforum.service.MovieService;
import com.example.movieforum.service.ReviewService;
import com.example.movieforum.validate.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;
    private final AuthService authService;

    public ReviewController(ReviewService reviewService, MovieService movieService, AuthService authService) {
        this.reviewService = reviewService;
        this.movieService = movieService;
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        try {
            Review review = reviewService.getReviewById(id);
            return ResponseEntity.ok(review);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable Long movieId) {
        try {
            List<Review> reviews = reviewService.getReviewsByMovie(movieId);
            return ResponseEntity.ok(reviews);
        } catch (MovieNotFoundException | ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        try {
            List<Review> reviews = reviewService.getReviewsByUser(userId);
            return ResponseEntity.ok(reviews);
        } catch (UserNotFoundException | ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<String> createReview(@PathVariable Long movieId, @RequestBody Review review) {
        try {
            review.setReviewer(authService.getCurrentUser());
            review.setMovie(movieService.getMovieById(movieId));
            Review savedReview = reviewService.saveReview(review);
            return ResponseEntity.ok(savedReview.toString());
        } catch (UserNotReviewerException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (ValidationException | NoUserLoggedInException | DataAccessException | ReviewAlreadyPostedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        try {
            updatedReview.setReviewer(authService.getCurrentUser());
            Review review = reviewService.updateReview(id, updatedReview);
            return ResponseEntity.ok(review.toString());
        } catch (UserNotReviewerException | UserNotReviewOwnerException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (ValidationException | NoUserLoggedInException | DataAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id, authService.getCurrentUser());
            return ResponseEntity.noContent().build();
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UserNotReviewerException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}