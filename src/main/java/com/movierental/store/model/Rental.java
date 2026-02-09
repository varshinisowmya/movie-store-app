package com.movierental.store.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Data
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many rentals can belong to one User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many rentals can be for one Movie (at different times)
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private boolean processed = false; // true when movie is returned
}