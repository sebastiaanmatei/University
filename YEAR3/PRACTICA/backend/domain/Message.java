package com.example.myplannerApp.domain;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "messages")
public class Message {
    String id;
    String idConversation;
    String content;
    String idSender;
    String timeStamp;

}
