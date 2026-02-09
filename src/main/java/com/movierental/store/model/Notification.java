package com.movierental.store.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Who is this message for?
    private String message; // e.g., "Payment Successful!"
    private LocalDateTime createdAt;
    private boolean isRead = false; // Default to unread
}