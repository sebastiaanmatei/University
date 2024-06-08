package com.example.myplannerApp.domain;

import com.example.myplannerApp.configs.SkillsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "workers")
public class Worker extends User{
    String available;
    @JsonDeserialize(using = SkillsDeserializer.class)
    List<String> skills;
}
