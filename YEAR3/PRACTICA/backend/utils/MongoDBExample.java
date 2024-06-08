//package com.example.myplannerApp.utils;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//public class MongoDBExample {
//    public static void main(String[] args) {
//        // MongoDB connection string (replace with your MongoDB Atlas connection string)
//        String connectionString = "mongodb+srv://smatei345:rPjq31MKzZoYCSdm@cluster0.6bl6z9y.mongodb.net/?retryWrites=true&w=majority";
//
//        // Create a MongoClient
//        MongoClient mongoClient = MongoClients.create(connectionString);
//
//        // Connect to the database
//        MongoDatabase database = mongoClient.getDatabase("myTaskPlanner");
//
//        // Get the "users" collection
//        MongoCollection<Document> usersCollection = database.getCollection("Users");
//
//        // Create a user document
//        Document userDocument = new Document()
//                .append("username", "dianacal")
//                .append("email", "diana@gmail.com")
//                .append("password", "abc");
//
//        // Insert the user document into the "users" collection
//        usersCollection.insertOne(userDocument);
//
//        // Close the MongoClient when done
//        mongoClient.close();
//    }
//}
//
