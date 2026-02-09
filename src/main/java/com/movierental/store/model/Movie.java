package com.movierental.store.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Tells Spring: "Make a table for this in MySQL"
@Table(name = "movies")
@Data   // Lombok: Automatically creates Getters, Setters, and toString()
public class Movie {

    @Id // Tells Spring: "This is the Primary Key (Unique ID)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically counts 1, 2, 3...
    private Long id;

    private String title;
    private String genre;
    private double pricePerDay;
    private boolean available = true; // Default to true when a movie is added
}