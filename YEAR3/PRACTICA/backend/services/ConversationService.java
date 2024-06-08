package com.example.myplannerApp.services;

import com.example.myplannerApp.domain.Conversation;
import com.example.myplannerApp.domain.Manager;
import com.example.myplannerApp.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Message> getMessagesForConversation(String conversationId) {
        // Create a query to find messages associated with the given conversationId
//        Query query = new Query(Criteria.where("idConversation").is(conversationId));
//        // Use the query to fetch messages
//        List<Message> ms=mongoTemplate.find(query, Message.class);


        Query query = new Query(Criteria.where("idSender").is("65326ca4ee588411e8929bd6"));
        List<Message> ms = mongoTemplate.find(query, Message.class);
//        System.out.println(ms.get(0));
        return ms;
    }
}

