package com.movierental.store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController tells Spring: "This class handles API requests"
@RestController
public class HelloController {

    // @GetMapping tells Spring: "When someone visits /hello, run this method"
    @GetMapping("/hello")
    public String sayHello() {
        return "Welcome to the Movie Rental Shop API!";
    }
}