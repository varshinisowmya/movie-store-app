package com.movierental.store.controller;

import com.movierental.store.model.Payment;
import com.movierental.store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // We send the Rental ID in the URL and the amount as a "Parameter"
    @PostMapping("/{rentalId}")
    public Payment makePayment(@PathVariable Long rentalId, @RequestParam double amount) {
        return paymentService.processPayment(rentalId, amount);
    }
}