package com.example.movieforum.service;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.Movie;
import com.example.movieforum.exception.MovieNotFoundException;
import com.example.movieforum.exception.UserNotMovieOwnerException;
import com.example.movieforum.repository.MovieRepository;
import com.example.movieforum.validate.movie.validator.MovieValidator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final MovieValidator movieValidator;

    public MovieService(MovieRepository movieRepository, AuthService authService) {
        this.movieRepository = movieRepository;
        this.movieValidator = new MovieValidator();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie saveMovie(Movie movie) {
        movieValidator.validate(movie);
        return movieRepository.save(movie);
    }

    public void importMovies(AppUser currentUser) throws FileNotFoundException {

            File importFile = new File("movies.txt");
            Scanner reader = new Scanner(importFile);
            while (reader.hasNextLine()) {
                String movieString = reader.nextLine();
                Movie movie = new Movie(movieString);
                movie.setUploader(currentUser);
                saveMovie(movie);
            }

    }



    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie updateMovie(Long id, Movie updatedMovie){
        Movie movie = getMovieById(id);
        if (!movie.getUploader().getId().equals(updatedMovie.getUploader().getId())) {
            throw new UserNotMovieOwnerException(updatedMovie.getUploader(), movie);
        }
        if (updatedMovie.getTitle() != null) {
            movie.setTitle(updatedMovie.getTitle());
        }
        if (updatedMovie.getReleaseDate() != null) {
            movie.setReleaseDate(updatedMovie.getReleaseDate());
        }
        if (updatedMovie.getRuntime() != null) {
            movie.setRuntime(updatedMovie.getRuntime());
        }
        if (updatedMovie.getBudget() != null) {
            movie.setBudget(updatedMovie.getBudget());
        }
        if (updatedMovie.getDescription() != null) {
            movie.setDescription(updatedMovie.getDescription());
        }
        return saveMovie(movie);
    }

    public void deleteMovie(Long id, AppUser currentUser) throws MovieNotFoundException {
        Movie movie = getMovieById(id);
        if (!movie.getUploader().getId().equals(currentUser.getId())) {
            throw new UserNotMovieOwnerException(currentUser, movie);
        }
        movieRepository.deleteById(id);
    }
}
