package com.example.practice2.controller;

import com.example.practice2.domain.Client;
import com.example.practice2.domain.Hotel;
import com.example.practice2.domain.OfferDetails;
import com.example.practice2.domain.SpecialOffer;
import com.example.practice2.service.Service;
import com.example.practice2.utils.events.EntityChangeEvent;
import com.example.practice2.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController implements Observer<EntityChangeEvent> {

    Service service;
    Client client;
    ObservableList<OfferDetails> model = FXCollections.observableArrayList();


    @FXML
    public TableView tableClient;
    @FXML
    public TableColumn col1cln;

    @FXML
    public TableColumn col2cln;

    @FXML
    public TableColumn col3cln;

    @FXML
    public TableColumn col4cln;

    @FXML
    public Label labelPuncte;

    @FXML
    public Label labelClient;


    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    void initData(Client section) {
        this.client = section;
        initialize();
        labelPuncte.setText(String.valueOf(client.getFidelityGrade()));
        labelClient.setText(String.valueOf(client.getName()));
        initModel(client);

    }

    void initialize() {
        col1cln.setCellValueFactory(new PropertyValueFactory<OfferDetails, String>("nameHotel"));
        col2cln.setCellValueFactory(new PropertyValueFactory<OfferDetails, String>("nameLocation"));
        col3cln.setCellValueFactory(new PropertyValueFactory<OfferDetails, Date>("startDate"));
        col4cln.setCellValueFactory(new PropertyValueFactory<OfferDetails, Date>("endDate"));
        tableClient.setItems(model);

    }

    private void initModel(Client c) {
        List<OfferDetails> messages = service.getDetails();
        List<OfferDetails> sections = StreamSupport.stream(messages.spliterator(), false)
                .filter(x->x.percents<=c.getFidelityGrade())
                .collect(Collectors.toList());
        model.setAll(sections);
    }

    @Override
    public void update(EntityChangeEvent entityChangeEvent) {

    }
}
