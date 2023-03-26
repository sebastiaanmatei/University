package com.example.practice2.controller;

import com.example.practice2.HelloApplication;
import com.example.practice2.domain.Client;
import com.example.practice2.domain.Hotel;
import com.example.practice2.domain.Location;
import com.example.practice2.service.Service;
import com.example.practice2.utils.events.EntityChangeEvent;
import com.example.practice2.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LocationController implements Observer<EntityChangeEvent> {

    Service service;
    ObservableList<Hotel> model = FXCollections.observableArrayList();

    @FXML
    public ComboBox comboLocations;

    @FXML
    public TableView tableHotels;

    @FXML
    public TableColumn col1hot;

    @FXML
    public TableColumn col2hot;

    @FXML
    public TableColumn col3hot;

    @FXML
    public TableColumn col4hot;

    @FXML
    public Button deschBook;



    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
        initialize();
    }

    void initialize() {
        col1hot.setCellValueFactory(new PropertyValueFactory<Hotel, String>("hotelName"));
        col2hot.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("noRooms"));
        col3hot.setCellValueFactory(new PropertyValueFactory<Hotel, Double>("pricePerNight"));
        col4hot.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("type"));
        tableHotels.setItems(model);
        List<Location> ls=service.getLocations();
        List<String>lls=new ArrayList<>();
        for(Location l:ls){
            lls.add(l.getLocationName());
        }

        ObservableList<String> options = FXCollections.observableArrayList(lls);
        comboLocations.setItems(options);


        List<Client> cls=service.getClients();
        //System.out.println("ajunge aici");
        for(Client dr:cls){
            ///System.out.println("intra for");
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("client.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            try {
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ClientController controller = loader.getController();
            controller.setService(service);
            controller.initData(dr);
            stage.show();
        }



    }

    private void initModel(Double id) {
        System.out.println("intra prin init");
        List<Hotel> messages = service.getHotLoc(id);
        List<Hotel> sections = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(sections);
    }

    @FXML
    private void handleBox(ActionEvent Event){
        String id= (String) comboLocations.getSelectionModel().getSelectedItem();
        double idd=service.getLocIdByName(id);
        System.out.println("intra prin box");
        initModel(idd);
    }

    public Stage showPage(Hotel sec) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("booking.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BookingController controller = loader.getController();
        controller.setService(service);
        controller.initData(sec);
        stage.show();
        return stage;
    }

    @FXML
    private void handleOpen(ActionEvent Event){
        Hotel sc = (Hotel) tableHotels.getSelectionModel().getSelectedItem();
        if (sc != null) {
            showPage(sc);
        }
    }


    @Override
    public void update(EntityChangeEvent entityChangeEvent) {

    }
}
