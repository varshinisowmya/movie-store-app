package com.movierental.store.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // We store the ID of the rental this payment is for
    private Long rentalId;

    private double amount;

    private String status; // We'll use "SUCCESS" or "FAILED"

    private LocalDateTime transactionDate;
}