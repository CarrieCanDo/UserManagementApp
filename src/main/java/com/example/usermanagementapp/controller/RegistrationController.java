package com.example.usermanagementapp.controller;

import com.example.usermanagementapp.entity.User;
import com.example.usermanagementapp.entity.Role;
import com.example.usermanagementapp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        // Check for validation errors
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        // Check if the username or email already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "error.user", "Username is already taken");
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "error.user", "Email is already in use");
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        // Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role to USER
        user.setRole(Role.USER);

        // Save the user if no errors
        userRepository.save(user);
        return ResponseEntity.ok("Success");
    }
}
