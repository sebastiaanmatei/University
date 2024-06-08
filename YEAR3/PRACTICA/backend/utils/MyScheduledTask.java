package com.example.myplannerApp.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyScheduledTask {

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void runTask() {
        // Your task logic goes here
        System.out.println("Scheduled task executed at: " + new Date());
    }
}

