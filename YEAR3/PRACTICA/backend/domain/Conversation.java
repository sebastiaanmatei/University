package com.example.myplannerApp.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "conversations")
public class Conversation {
    String id;
    String idAcceptedTask;
    String idWorker;
    String idAssigner;
}
