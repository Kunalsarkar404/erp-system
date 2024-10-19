package com.example.erp_system.service;

import com.example.erp_system.entity.User;
import com.example.erp_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Repository for user database operations

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Check if the user already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role if not provided
        if (user.getRoles().isEmpty()) {
            user.getRoles().add("USER"); // Assign default USER role
        }

        // Save the user to the database
        return userRepository.save(user);
    }
}
