package com.movierental.store.service;

import com.movierental.store.model.Movie;
import com.movierental.store.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Tells Spring: "This is a business logic class"
public class MovieService {

    @Autowired // This tells Spring to "plug in" the Repository here automatically
    private MovieRepository movieRepository;

    // CREATE: Save a movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // READ: Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    // UPDATE: Change movie details
    public Movie updateMovie(Long id, Movie movieDetails) {
        // Find the movie first, or throw an error if it doesn't exist
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        movie.setPricePerDay(movieDetails.getPricePerDay());
        movie.setAvailable(movieDetails.isAvailable());

        return movieRepository.save(movie);
    }

    // DELETE: Remove a movie
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}