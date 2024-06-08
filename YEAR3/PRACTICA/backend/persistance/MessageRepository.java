package com.example.myplannerApp.persistance;



import com.example.myplannerApp.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findContentByIdConversationOrderByTimeStampDesc(String idConversation);

    List<Message> findByIdConversation(String idConversation);
}

