package com.example.myplannerApp.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "managers")
public class Manager2{
    String id;
    String username;
    String email;
    String password;
    String company;
}

