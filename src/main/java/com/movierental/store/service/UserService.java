package com.movierental.store.service;

import com.movierental.store.model.User;
import com.movierental.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // REGISTER: Save a new user
    public User registerUser(User user) {
        // In a real app, we would encrypt the password here!
        return userRepository.save(user);
    }

    // LOGIN: Check if username and password match
    public String login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return "Login Successful! Role: " + user.get().getRole();
        }
        return "Invalid Username or Password";
    }
}