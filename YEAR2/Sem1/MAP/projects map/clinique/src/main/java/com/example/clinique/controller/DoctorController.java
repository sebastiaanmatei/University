package com.example.clinique.controller;

import com.example.clinique.domain.Checkup;
import com.example.clinique.domain.Doctor;
import com.example.clinique.domain.Section;
import com.example.clinique.service.Service;
import com.example.clinique.utils.events.CheckupEntityChangeEvent;
import com.example.clinique.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DoctorController implements Observer<CheckupEntityChangeEvent> {

    Service service;

    Doctor dr;
    ObservableList<Checkup> model = FXCollections.observableArrayList();

    @FXML
    public Label nameDoctorLabel;

    @FXML
    public TableView tabelDoctor;

    @FXML
    public TableColumn col1tab;

    @FXML
    public TableColumn col2tab;

    @FXML
    public TableColumn col3tab;

    @FXML
    public TableColumn col4tab;


    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
        initialize();
    }

    void initData(Doctor doc) {
        this.dr = doc;
        nameDoctorLabel.setText("Dr."+dr.getName());
        initialize();
        List<Checkup> messages = service.getListCheckupsForDoctor(dr.getIdDoctor());
        List<Checkup> checkups = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(checkups);

    }
    void initialize() {
        col1tab.setCellValueFactory(new PropertyValueFactory<Checkup, Integer>("idPacient"));
        col2tab.setCellValueFactory(new PropertyValueFactory<Checkup, String>("namePacient"));
        col3tab.setCellValueFactory(new PropertyValueFactory<Checkup, LocalDate>("date"));
        col4tab.setCellValueFactory(new PropertyValueFactory<Checkup, Integer>("hour"));
        tabelDoctor.setItems(model);

    }
    @Override
    public void update(CheckupEntityChangeEvent checkupEntityChangeEvent) {

    }
}
