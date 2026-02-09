package com.movierental.store.service;

import com.movierental.store.model.Movie;
import com.movierental.store.model.Rental;
import com.movierental.store.model.User;
import com.movierental.store.repository.MovieRepository;
import com.movierental.store.repository.RentalRepository;
import com.movierental.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    public Rental rentMovie(Long userId, Long movieId) {
        // 1. Find the User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Find the Movie
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // 3. Check if Movie is available
        if (!movie.isAvailable()) {
            throw new RuntimeException("Movie is already rented out!");
        }

        // 4. Update Movie status to not available
        movie.setAvailable(false);
        movieRepository.save(movie);

        // 5. Create the Rental record
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setMovie(movie);
        rental.setRentalDate(LocalDate.now());

        return rentalRepository.save(rental);
    }
    public Rental returnMovie(Long rentalId) {
        // 1. Find the rental record
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental record not found"));

        // 2. Make the movie available again
        Movie movie = rental.getMovie();
        movie.setAvailable(true);
        movieRepository.save(movie);

        // 3. Mark the rental as processed and set return date
        rental.setProcessed(true);
        rental.setReturnDate(LocalDate.now());

        return rentalRepository.save(rental);
    }
}