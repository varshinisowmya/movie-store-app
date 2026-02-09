package com.movierental.store.controller;

import com.movierental.store.model.Movie;
import com.movierental.store.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies") // All URLs in this class will start with /api/movies
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // POST request to add a movie (Sending data TO the server)
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        // @RequestBody means: "Look at the JSON data coming from Postman/Frontend"
        return movieService.addMovie(movie);
    }

    // GET request to see all movies
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // PUT request to update (e.g., /api/movies/1)
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        return movieService.updateMovie(id, movieDetails);
    }

    // DELETE request to remove (e.g., /api/movies/1)
    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "Movie deleted successfully!";
    }
}