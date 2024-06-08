package com.example.myplannerApp.persistance;

import com.example.myplannerApp.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findBySkillRequiredIn(List<String> skills);

    // Additional custom queries can be defined here
    /////////
}

