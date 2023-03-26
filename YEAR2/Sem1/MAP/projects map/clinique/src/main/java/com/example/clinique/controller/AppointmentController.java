package com.example.clinique.controller;

import com.example.clinique.domain.Doctor;
import com.example.clinique.domain.Section;
import com.example.clinique.service.Service;
import com.example.clinique.utils.events.CheckupEntityChangeEvent;
import com.example.clinique.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AppointmentController implements Observer<CheckupEntityChangeEvent> {

    Service service;
    ObservableList<Doctor> model = FXCollections.observableArrayList();
    Section selectedSection;

    @FXML
    public TableColumn col1d;

    @FXML
    public TableView doctorsTableAp;

    @FXML
    public TextField filedNumeAp;

    @FXML
    public DatePicker datePickerAp;

    @FXML
    public ComboBox comboOraAp;

    @FXML
    public Button buttonAppointment;


    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    void initData(Section section) {
        this.selectedSection = section;
        initialize();
        List<Doctor> messages = service.getListDoctorsAtSection(section);
        List<Doctor> doctors = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(doctors);

    }

    void initialize() {
        col1d.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        doctorsTableAp.setItems(model);
        ObservableList<Integer> options =
                FXCollections.observableArrayList(
                        1,2,3,4,5
                );
        comboOraAp.setItems(options);
    }

    @FXML
    void handleAppointment(ActionEvent Event) {
        Doctor dr = (Doctor) doctorsTableAp.getSelectionModel().getSelectedItem();
        Integer idDoc=dr.getIdDoctor();
        System.out.println(idDoc);
        String namePacient=filedNumeAp.getText();
        LocalDate dt=datePickerAp.getValue();
        Integer hour= (Integer) comboOraAp.getSelectionModel().getSelectedItem();
        Random rand = new Random();
        int upperbound = 600000000;
        int cnp=rand.nextInt(upperbound);
        service.addCheck(idDoc,cnp,namePacient,dt,hour);

    }

    @Override
    public void update(CheckupEntityChangeEvent userEntityChangeEvent) {

    }



}
