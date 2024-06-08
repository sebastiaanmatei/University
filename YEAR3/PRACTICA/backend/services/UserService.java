package com.example.myplannerApp.services;

import com.example.myplannerApp.domain.Manager;
import com.example.myplannerApp.domain.Manager2;
import com.example.myplannerApp.domain.Worker;
import com.example.myplannerApp.persistance.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ManagerRepository workerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public Manager findManagerByUsernameAndPassword(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        return mongoTemplate.findOne(query, Manager.class);
    }

    public Worker findWorkerByUsernameAndPassword(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        return mongoTemplate.findOne(query, Worker.class);
    }

}


