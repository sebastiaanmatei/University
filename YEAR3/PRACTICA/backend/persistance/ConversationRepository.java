package com.example.myplannerApp.persistance;

import com.example.myplannerApp.domain.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

    List<Conversation> findByIdWorker(String id);

    List<Conversation> findByIdAssigner(String id);

    Conversation findByIdWorkerAndIdAssigner(String idWorker, String idAssigner);


}

