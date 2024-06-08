package com.example.myplannerApp.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "conversationHeads")
public class ConversationHead {
    String username;
    String lastMessage;
    String idConversation;
    User otherConverser;
}
