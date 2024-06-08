package com.example.myplannerApp.domain;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "taskexecs")
public class TaskExecution {
    String who;
    String what;
    String Description;
    String progress;
}
