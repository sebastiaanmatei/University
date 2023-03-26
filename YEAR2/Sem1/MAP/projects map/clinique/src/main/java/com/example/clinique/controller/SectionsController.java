package com.example.clinique.controller;

import com.example.clinique.HelloApplication;
import com.example.clinique.domain.Checkup;
import com.example.clinique.domain.Doctor;
import com.example.clinique.domain.Section;
import com.example.clinique.service.Service;
import com.example.clinique.utils.events.CheckupEntityChangeEvent;
import com.example.clinique.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SectionsController implements Observer<CheckupEntityChangeEvent> {

    Service service;
    ObservableList<Section> model = FXCollections.observableArrayList();
    ObservableList<Checkup> Amodel = FXCollections.observableArrayList();

    @FXML
    public TableView sectionsTable;
    @FXML
    public Button appointmentButton;
    @FXML
    public TableColumn col1s;
    @FXML
    public TableColumn col2s;
    @FXML
    public TableColumn col3s;
    @FXML
    public TableColumn col4s;

    @FXML
    public TextField fieldSort;

    @FXML
    public Button buttonSort;

    @FXML
    public TableColumn col1dra;
    @FXML
    public TableColumn col2dra;
    @FXML
    public TableColumn col3dra;

    @FXML
    public TableView tableDrAp;



    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
        initialize();
        initModel();
    }

    void initialize() {
        col1s.setCellValueFactory(new PropertyValueFactory<Section, String>("name"));
        col2s.setCellValueFactory(new PropertyValueFactory<Section, Integer>("idSectionChief"));
        col3s.setCellValueFactory(new PropertyValueFactory<Section, Integer>("pricePerCheckup"));
        col4s.setCellValueFactory(new PropertyValueFactory<Section, Integer>("maxDurationCheckup"));
        sectionsTable.setItems(model);
        col1dra.setCellValueFactory(new PropertyValueFactory<Checkup, String>("namePacient"));
        col2dra.setCellValueFactory(new PropertyValueFactory<Checkup, LocalDate>("date"));
        col3dra.setCellValueFactory(new PropertyValueFactory<Checkup, Integer>("hour"));
        tableDrAp.setItems(Amodel);

        List<Doctor> docs=service.getListDoctors();

        for(Doctor dr:docs){
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("doctor.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            try {
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            DoctorController controller = loader.getController();
            controller.setService(service);
            System.out.println(dr.getName());
            controller.initData(dr);
            stage.show();
        }



    }

    private void initModel() {
        List<Section> messages = service.getListSections();
        List<Section> sections = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(sections);
    }

    public Stage showPage(Section sec) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("appointment.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AppointmentController controller = loader.getController();
        controller.setService(service);
        controller.initData(sec);
        stage.show();
        return stage;
    }

    @FXML
    void AppointmentHandle(ActionEvent Event) {
        Section sc = (Section) sectionsTable.getSelectionModel().getSelectedItem();
        if (sc != null) {
            showPage(sc);
        }
    }

    @FXML
    void handleSort(ActionEvent Event) {
        int idDoc=0;
        String dr=fieldSort.getText();
        List<Doctor> docs=service.getListDoctors();
        for(Doctor drs:docs){
            if(drs.getName().equals(dr)){
                idDoc=drs.getId();
            }
        }
        List<Checkup> checks=service.getListCheckupsForDoctor(idDoc);
        List<Checkup> checkups = StreamSupport.stream(checks.spliterator(), false)
                .collect(Collectors.toList());

        Collections.sort(checkups, new Comparator<Checkup>() {
            public int compare(Checkup o1, Checkup o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        Amodel.setAll(checkups);

    }




    @Override
    public void update(CheckupEntityChangeEvent checkupEntityChangeEvent) {

    }
}
