package com.example.HbProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HbProject.entity.User;
import com.example.HbProject.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register
    public String register(User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // Login
    public String login(String usernameOrEmail, String password) {

        Optional<User> user =
                userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if(user.isEmpty()) {
            return "Invalid username/email";
        }

        if(passwordEncoder.matches(password, user.get().getPassword())) {
            return "Login Successful";
        }

        return "Invalid Password";
    }
}