package com.example.myplannerApp.controllers;

import com.example.myplannerApp.domain.*;
import com.example.myplannerApp.persistance.*;
import com.example.myplannerApp.services.ConversationService;
import com.example.myplannerApp.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.myplannerApp.domain.Task;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messr;

    @Autowired
    private ConversationRepository cvr;

    @Autowired
    private WorkerRepository wkr;

    @Autowired
    private TaskRepository tkr;

    @Autowired
    private ManagerRepository mnr;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MongoTemplate mongoTemplate;



    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/newTaskUpdates")
    @SendTo("/topic/tasksUpdated")
    public void handleUpdateTask() {
        System.out.println("trece prin jeg");
        // Handle the 'updateTask' event here, process it, and then send updates to connected clients
        String updatedMessage = "Tasks updated";

        messagingTemplate.convertAndSend("/topic/tasksUpdated", updatedMessage);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/acceptTask")
    @SendTo("/topic/accTask")
    public void handleAccTask(@Payload String ids) {

        String[] parts = ids.split(",");
        String idTask = parts[0].trim();
        String idWorker = parts[1];


        Task t = tkr.findById(idTask).orElse(null);
        System.out.println(t.getTitle());

        if(t!= null){

            String idManager = t.getIdManager();
            Conversation conv = cvr.findByIdWorkerAndIdAssigner(idWorker, idManager);

            OffsetDateTime currentTimestamp = OffsetDateTime.now(ZoneOffset.UTC);
            Duration twoHours = Duration.ofHours(2);
            OffsetDateTime timestampTwoHoursLater = currentTimestamp.plus(twoHours);
            String formattedTimestamp = timestampTwoHoursLater.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            if(conv!= null){
                System.out.println("aceeasi conversatie");
                conv.setIdAcceptedTask(idTask);
                String idd = RandomStringGenerator.generateRandomString();
                Message mess = new Message(idd, conv.getId(), "NEW TASK ACCEPTED : "+ t.getTitle(), idWorker, formattedTimestamp);
                messr.insert(mess);
                tkr.delete(t);
                List<Message> messages = messr.findByIdConversation(conv.getId());
                messagingTemplate.convertAndSend("/topic/accTask", messages);

            }else{
                System.out.println("conversatie noua");
                String idd = RandomStringGenerator.generateRandomString();
                Conversation newconv = new Conversation(idd, idTask, idWorker, idManager);
                cvr.insert(newconv);
                String iddd = RandomStringGenerator.generateRandomString();
                Worker w = wkr.findById(idWorker).orElse(null);
                Message mess = new Message(iddd, newconv.getId(), "This is an automated message. Worker "+ w.getUsername()+" accepted the task: "+ t.getTitle()+".", idWorker, formattedTimestamp);
                messr.insert(mess);
                tkr.delete(t);
                List<Message> messages = messr.findByIdConversation(newconv.getId());
                messagingTemplate.convertAndSend("/topic/accTask", messages);
            }

        }
        String updatedMessage = "Tasks updated";

        messagingTemplate.convertAndSend("/topic/accTask", updatedMessage);
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/sendMessage")
    @SendTo("/topic/newMessages")
    public List<Message> handleSendMessage(@Payload Message message) {


        System.out.println("message received: "+message.getContent()+" id sender "+message.getIdSender());
        OffsetDateTime currentTimestamp = OffsetDateTime.now(ZoneOffset.UTC);

        // Add 2 hours to the current timestamp
        Duration twoHours = Duration.ofHours(2);
        OffsetDateTime timestampTwoHoursLater = currentTimestamp.plus(twoHours);

        // Format the timestamp in the desired format
        String formattedTimestamp = timestampTwoHoursLater.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        message.setTimeStamp(formattedTimestamp);
        System.out.println(message.getTimeStamp());
        messr.insert(message);
        List<Message> messages = messr.findByIdConversation(message.getIdConversation());

        messagingTemplate.convertAndSend("/topic/updatecc", message);


        System.out.println("message added");
        return messages;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/fetchConversation")
    @SendTo("/topic/conversations")
    public List<Message> handleConvFetch(@Payload String idConversation) {

        String[] parts = idConversation.split(",");
        String id = parts[0].trim();
        String idUser = parts[1];

        // Retrieve messages based on the conversation ID
        System.out.println("id conversatie: "+id);
        List<Message> messages = messr.findByIdConversation(id);

        Query query = new Query(Criteria.where("id").is(id));
        Conversation cnv = mongoTemplate.findOne(query, Conversation.class);

        Conversation cnn = cvr.findById(id).orElse(null);
        System.out.println(cnv == null);

        User us = null;

        if(cnn!=null){
            System.out.println("s a gasit conversatia");
            if(cnn.getIdAssigner().equals(idUser)){
                us=wkr.findById(cnn.getIdWorker()).orElse(null);
            }else{
                us = mnr.findById(cnn.getIdAssigner()).orElse(null);
            }
        }

        String msg;
        if(us!= null){
            System.out.println("s a gasit user");
            msg = (us.getUsername()+","+idConversation).toString();
        }else{
            msg = "user not available,"+idConversation;
        }

        messagingTemplate.convertAndSend("/topic/userconv", msg);

        System.out.println("Printed messages");

        return messages;
    }








}


