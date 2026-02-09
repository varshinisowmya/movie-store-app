package com.movierental.store.service;

import com.movierental.store.model.Payment;
import com.movierental.store.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // We inject the NotificationService so we can send alerts
    @Autowired
    private NotificationService notificationService;

    public Payment processPayment(Long rentalId, double amount) {
        Payment payment = new Payment();
        payment.setRentalId(rentalId);
        payment.setAmount(amount);
        payment.setTransactionDate(LocalDateTime.now());

        if (amount > 0) {
            payment.setStatus("SUCCESS");

            // Logic: Trigger the notification automatically upon success
            // We use 1L as a placeholder for User ID #1
            notificationService.send(1L, "Payment of $" + amount + " successful for Rental #" + rentalId);
        } else {
            payment.setStatus("FAILED");
        }

        return paymentRepository.save(payment);
    }
}