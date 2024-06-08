package com.example.myplannerApp.tests;

import com.example.myplannerApp.controllers.WebSocketController;
import com.example.myplannerApp.controllers.Controller;
import com.example.myplannerApp.domain.Message;
import com.example.myplannerApp.domain.Task;
import com.example.myplannerApp.persistance.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.example.myplannerApp.controllers")
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void handleUpdateTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/newTaskUpdates"))
                .andExpect(status().isOk());
    }

    @Test
    void handleAccTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/acceptTask")
                        .content("taskId,workerId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handleSendMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sendMessage")
                        .content(objectMapper.writeValueAsString(new Message()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handleConvFetch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/fetchConversation")
                        .content("conversationId,userId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addTask() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/newTask")
                        .content(objectMapper.writeValueAsString(task))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllTasks() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        Mockito.when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/tasks"))
                .andExpect(status().isOk());
    }
}

