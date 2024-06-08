package com.example.myplannerApp.persistance;

import com.example.myplannerApp.domain.Conversation;
import com.example.myplannerApp.domain.ConversationHead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConversationHeadRepository extends MongoRepository<ConversationHead, Integer> {

    // Additional custom queries can be defined here
}


