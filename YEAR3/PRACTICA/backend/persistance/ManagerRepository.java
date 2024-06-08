package com.example.myplannerApp.persistance;

import com.example.myplannerApp.domain.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager, String> {

    Manager findByUsernameAndPassword(String username, String password);

//    @Query("{'_id':  ?0}")
//    Optional<Manager> findById(String id);
    // Additional custom queries can be defined here
}