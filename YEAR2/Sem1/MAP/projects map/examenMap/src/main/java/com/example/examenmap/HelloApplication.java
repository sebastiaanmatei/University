package com.example.examenmap;

import com.example.examenmap.controller.PrincController;
import com.example.examenmap.repo.NevoiRepository;
import com.example.examenmap.repo.PersoaneRepository;
import com.example.examenmap.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {

    Service service;
    String username = "postgres";
    String password = "vivalavega";
    String url = "jdbc:postgresql://localhost:5432/examenmap";


    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        PersoaneRepository prepo=new PersoaneRepository(url, username, password);
        NevoiRepository nrepo=new NevoiRepository(url, username, password);

        service = new Service(prepo, nrepo);
        initView(primaryStage);
        primaryStage.setWidth(800);
        primaryStage.show();


    }

    private void initView(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("princ.fxml"));
        VBox userLayout = fxmlLoader.load();
        primaryStage.setScene(new Scene(userLayout));
        PrincController sec = fxmlLoader.getController();
        sec.setService(service);

    }
}