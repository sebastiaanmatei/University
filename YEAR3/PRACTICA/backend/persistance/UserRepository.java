package com.example.myplannerApp.persistance;

import com.example.myplannerApp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    // Additional custom queries can be defined here
}
