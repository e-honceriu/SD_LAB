package com.example.movieforum.controller;

import com.example.movieforum.entity.Movie;
import com.example.movieforum.exception.MovieNotFoundException;
import com.example.movieforum.exception.NoUserLoggedInException;
import com.example.movieforum.exception.UserNotMovieOwnerException;
import com.example.movieforum.service.AuthService;
import com.example.movieforum.service.MovieService;
import com.example.movieforum.validate.ValidationException;
import com.example.movieforum.validate.user.exception.UserNotUploaderException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;
    private final AuthService authService;
    public MovieController(MovieService movieService, AuthService authService) {
        this.movieService = movieService;
        this.authService = authService;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        try {
            Movie movie = movieService.getMovieById(movieId);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = movieService.getMovies();
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<String> createMovie(@RequestBody Movie movie) {
        try {
            movie.setUploader(authService.getCurrentUser());
            Movie savedMovie = movieService.saveMovie(movie);
            return ResponseEntity.ok(savedMovie.toString());
        } catch (UserNotUploaderException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (ValidationException | NoUserLoggedInException | DataAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<String> updateMovie(@PathVariable Long movieId, @RequestBody Movie updatedMovie) {
        try {
            updatedMovie.setUploader(authService.getCurrentUser());
            Movie updated = movieService.updateMovie(movieId, updatedMovie);
            return ResponseEntity.ok(updated.toString());
        } catch (UserNotUploaderException | UserNotMovieOwnerException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (ValidationException | NoUserLoggedInException | DataAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/import")
    public ResponseEntity<String> importMovies() {
        try {
            this.movieService.importMovies(this.authService.getCurrentUser());
        } catch ( NoUserLoggedInException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (FileNotFoundException e) {
            return ResponseEntity.badRequest().body("File was not found");
        }
        return ResponseEntity.ok("Movies were imported");
    }
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {
        try {
            movieService.deleteMovie(movieId, authService.getCurrentUser());
            return ResponseEntity.noContent().build();
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UserNotMovieOwnerException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}