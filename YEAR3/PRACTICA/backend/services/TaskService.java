package com.example.myplannerApp.services;

import com.example.myplannerApp.domain.Manager;
import com.example.myplannerApp.domain.Task;
import com.example.myplannerApp.persistance.ManagerRepository;
import com.example.myplannerApp.persistance.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> findFiltered(List<String> skills){
        return taskRepository.findBySkillRequiredIn(skills);
    }


}
