package com.example.practice2;

import com.example.practice2.controller.LocationController;
import com.example.practice2.domain.Client;
import com.example.practice2.repo.ClientRepo;
import com.example.practice2.repo.HotelRepo;
import com.example.practice2.repo.LocationRepo;
import com.example.practice2.repo.OfferRepo;
import com.example.practice2.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    ClientRepo crepo;
    LocationRepo lrepo;
    HotelRepo hrepo;
    OfferRepo orepo;

    Service service;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        String username = "postgres";
        String password = "vivalavega";
        String url = "jdbc:postgresql://localhost:5432/eximtur";
        ClientRepo crepo=new ClientRepo(url, username, password);
        HotelRepo hrepo=new HotelRepo(url, username, password);
        LocationRepo lrepo=new LocationRepo(url, username, password);
        OfferRepo orepo=new OfferRepo(url, username, password);


        service = new Service(lrepo, hrepo, orepo, crepo);
        initView(primaryStage);
        primaryStage.setWidth(800);
        primaryStage.show();


    }

    private void initView(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("location.fxml"));
        VBox userLayout = fxmlLoader.load();
        primaryStage.setScene(new Scene(userLayout));
        LocationController sec = fxmlLoader.getController();
        sec.setService(service);

    }
}