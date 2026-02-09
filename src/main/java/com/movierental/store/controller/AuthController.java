package com.movierental.store.controller;

import com.movierental.store.model.User;
import com.movierental.store.repository.UserRepository;
import com.movierental.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password) {
        System.out.println("Login attempt for: " + username); // Look at IntelliJ console for this!

        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println("Registering user: " + user.getUsername());
        return userRepository.save(user);
    }
}