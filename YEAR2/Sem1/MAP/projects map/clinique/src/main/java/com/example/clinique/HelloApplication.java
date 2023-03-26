package com.example.clinique;

import com.example.clinique.controller.SectionsController;
import com.example.clinique.domain.Doctor;
import com.example.clinique.repo.CheckupRepo;
import com.example.clinique.repo.DoctorRepo;
import com.example.clinique.repo.SectionRepo;
import com.example.clinique.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    CheckupRepo checkRepo;
    DoctorRepo drRepo;
    SectionRepo secRepo;
    Service service;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        String username = "postgres";
        String password = "vivalavega";
        String url = "jdbc:postgresql://localhost:5432/clinique";
        CheckupRepo checkRepo = new CheckupRepo(url, username, password);
        DoctorRepo drRepo = new DoctorRepo(url, username, password);
        SectionRepo secRepo = new SectionRepo(url, username, password);


        service = new Service(checkRepo, secRepo, drRepo);
        initView(primaryStage);
        primaryStage.setWidth(800);
        primaryStage.show();


    }

    private void initView(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sectionsView.fxml"));
        VBox userLayout = fxmlLoader.load();
        primaryStage.setScene(new Scene(userLayout));
        SectionsController sec = fxmlLoader.getController();
        sec.setService(service);

    }

}