package com.example.myplannerApp.controllers;

import com.example.myplannerApp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String userType; // 'manager' or 'worker'
    private User user; // Include the user data

    // Constructors, getters, and setters
}

