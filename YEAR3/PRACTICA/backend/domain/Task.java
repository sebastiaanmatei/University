package com.example.myplannerApp.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "tasks")
public class Task {
    String id;
    String title;
    String description;
    String idManager;
    String skillRequired;
}
