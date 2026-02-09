package com.movierental.store.controller;

import com.movierental.store.model.Rental;
import com.movierental.store.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/{userId}/{movieId}")
    public Rental rentMovie(@PathVariable Long userId, @PathVariable Long movieId) {
        return rentalService.rentMovie(userId, movieId);
    }
    @PutMapping("/return/{rentalId}")
    public Rental returnMovie(@PathVariable Long rentalId) {
        return rentalService.returnMovie(rentalId);
    }
}