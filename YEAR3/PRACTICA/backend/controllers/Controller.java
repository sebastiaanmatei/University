package com.example.myplannerApp.controllers;

import com.example.myplannerApp.domain.*;
import com.example.myplannerApp.persistance.*;
import com.example.myplannerApp.services.TaskService;
import com.example.myplannerApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ManagerRepository assignerRepository;
    @Autowired
    private ConversationRepository convRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService service;
    @Autowired
    TaskService taskService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private final SimpMessagingTemplate messagingTemplate;



//    public Optional<Manager2> findLogger(String username, String password) {
//        return service.authenticateAndGetUser(username, password);
//    }

//    public Optional<Manager> findID(String id) {
//        return service.findUser(id);
//    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/newTask")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        System.out.println(task.getTitle()+" "+task.getDescription());
        taskService.saveTask(task);

//        messagingTemplate.convertAndSend("/topic/newTask", task);
//        messagingTemplate.convertAndSend("/topic/newTask", task);


        return ResponseEntity.ok("Task added successfully");
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/filteredTasks")
    public List<Task> getFilteredTasks(@RequestParam List<String> skills) {
        return taskService.findFiltered(skills);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/filteredConversations")
    public List<ConversationHead> getFilteredConversations(@RequestParam String id) {
        System.out.println(id);
        List<ConversationHead> conversationInfoList = new ArrayList<>();

        // Convert userId to a Long if necessary

        if (workerRepository.existsById(id)) {
            List<Conversation> conversations = convRepository.findByIdWorker(id);
            for (Conversation conversation : conversations) {
                ConversationHead conversationInfo = new ConversationHead();
                User otherUser = getUserInfoM(conversation.getIdAssigner());
                conversationInfo.setUsername(otherUser.getUsername());
                conversationInfo.setIdConversation(conversation.getId());
                // Use a proper way to get the last message here
                Manager m = assignerRepository.findById(conversation.getIdAssigner()).orElse(null);



                String msg = messageRepository.findContentByIdConversationOrderByTimeStampDesc(conversation.getId()).toString();
                String content = extractContent(msg);
                conversationInfo.setLastMessage(content);
                conversationInfo.setOtherConverser(m);
                conversationInfoList.add(conversationInfo);
            }
        } else {
            List<Conversation> conversations = convRepository.findByIdAssigner(id);
            for (Conversation conversation : conversations) {
                ConversationHead conversationInfo = new ConversationHead();
                User otherUser = getUserInfoW(conversation.getIdWorker());
                conversationInfo.setUsername(otherUser.getUsername());
                conversationInfo.setIdConversation(conversation.getId());

                Worker m = workerRepository.findById(conversation.getIdWorker()).orElse(null);


                // Use a proper way to get the last message here
                //
                String msg = messageRepository.findContentByIdConversationOrderByTimeStampDesc(conversation.getId()).toString();
                String content = extractContent(msg);
                conversationInfo.setLastMessage(content);
                conversationInfo.setOtherConverser(m);
                conversationInfoList.add(conversationInfo);
            }
        }

        return conversationInfoList;
    }



    private Worker getUserInfoW(String userId) {
        return workerRepository.findById(userId).orElse(null);
    }

    private Manager getUserInfoM(String userId) {
        return assignerRepository.findById(userId).orElse(null);
    }

    public String extractContent(String receivedMessage) {
        // Define a regex pattern to match the content field
        Pattern pattern = Pattern.compile("content=([^,]+)");

        // Use a Matcher to find the content value
        Matcher matcher = pattern.matcher(receivedMessage);

        if (matcher.find()) {
            // Extract the content value (group 1 in the regex)
            String content = matcher.group(1);
            return content;
        } else {
            // Handle the case where no content is found
            return "no messages yet";
        }
    }



//    private String getLastMessage(String idConversation) {
////        List<Message> messages = messageRepository.findByConversation(idConversation);
//////        List<Message> messages = null;
////        if (!messages.isEmpty()) {
////            return messages.get(0).getFormattedMessage();
////        }
////        return "No messages";
//    }





    @GetMapping("/all")
    public List<User> getAllUsers() {
        // Retrieve all users from the MongoDB collection
        List<User> users = userRepository.findAll();

        // Map the User objects to UserDTO for JSON serialization

        return users.stream()
                .map(user -> new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()))
                .collect(Collectors.toList());
    }

    @PostMapping("/sebi")
    public User getAllUsersNew() {
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/newWorker")
    public ResponseEntity<SignupResponse> addWorker(@RequestBody Worker worker) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(20);
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }
        worker.setId(String.valueOf(randomString));
        workerRepository.save(worker);

        return ResponseEntity.ok(new SignupResponse(true, worker));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/newManager")
    public ResponseEntity<String> addAssigner(@RequestBody Manager manager) {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(20);
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }


//        manager.setId(String.valueOf(randomString));
//        assignerRepository.save(manager);
        return ResponseEntity.ok("Manager added successfully!");
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserCredentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        System.out.println(username+" "+password);

        Manager authenticatedUser = service.findManagerByUsernameAndPassword(username, password);

        if (authenticatedUser != null) {

            System.out.println(authenticatedUser.getUsername()+" "+authenticatedUser.getId()+" manager");
            return ResponseEntity.ok(new LoginResponse(true, "manager", authenticatedUser));
        } else{
            Worker authenticatedWorker = service.findWorkerByUsernameAndPassword(username, password);
            if(authenticatedWorker != null){
                System.out.println(authenticatedWorker.getUsername()+" "+authenticatedWorker.getPassword()+" worker");
                return ResponseEntity.ok(new LoginResponse(true, "worker", authenticatedWorker));
            }
            return ResponseEntity.ok(new LoginResponse(false, null, null));
        }

    }


}
