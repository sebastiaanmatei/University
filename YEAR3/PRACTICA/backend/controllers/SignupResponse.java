package com.example.myplannerApp.controllers;

import com.example.myplannerApp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupResponse {
    private boolean success;
    private User user; // Include the user data

    // Constructors, getters, and setters
}