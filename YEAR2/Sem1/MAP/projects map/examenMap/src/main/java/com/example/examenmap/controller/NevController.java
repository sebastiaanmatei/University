package com.example.examenmap.controller;

import com.example.examenmap.domain.Nevoie;
import com.example.examenmap.domain.Persoana;
import com.example.examenmap.domain.nevPers;
import com.example.examenmap.service.Service;
import com.example.examenmap.utils.events.EntityChangeEvent;
import com.example.examenmap.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NevController implements Observer<EntityChangeEvent> {

    Service service;
    Persoana pers;
    ObservableList<Nevoie> model = FXCollections.observableArrayList();
    ObservableList<Nevoie> model2 = FXCollections.observableArrayList();


    @FXML
    public TableView tabelNevoi;

    @FXML
    public TableView tabelFapte;

    @FXML
    public TableColumn col1nev;
    @FXML
    public TableColumn col2nev;

    @FXML
    public TableColumn col3nev;

    @FXML
    public TableColumn col4nev;

    @FXML
    public TableColumn col5nev;

    @FXML
    public TableColumn col6nev;

    @FXML
    public TableColumn col1fap;
    @FXML
    public TableColumn col2fap;
    @FXML
    public TableColumn col3fap;
    @FXML
    public TableColumn col4fap;
    @FXML
    public TableColumn col5fap;
    @FXML
    public TableColumn col6fap;

    @FXML
    public TextField fieldTitluNev;
    @FXML
    public TextField fieldDescNev;
    @FXML
    public DatePicker pickerNev;

    @FXML
    public Button butonCerere;

    @FXML
    public Button butonSalv;




    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    void initData(Persoana section) {
        this.pers = section;
        initialize();
        initModelNev();
        initModelFap(pers);

    }
    private void initModelNev() {
        List<Nevoie> messages = service.getListNevsForUserOras(pers);
        List<Nevoie> sections = StreamSupport.stream(messages.spliterator(), false)
                .filter(x->x.getOnSalvator().equals(0))
                .collect(Collectors.toList());
        model.setAll(sections);
    }
    private void initModelFap(Persoana p) {
        List<Nevoie> messages = service.getListNevs();
        List<Nevoie> sections = StreamSupport.stream(messages.spliterator(), false)
                .filter(x->x.getOnSalvator().equals(p.getId()))
                .collect(Collectors.toList());
        model2.setAll(sections);
    }

    void initialize() {
        col1nev.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("titlu"));
        col2nev.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("descriere"));
        col3nev.setCellValueFactory(new PropertyValueFactory<Nevoie, Date>("deadline"));
        col4nev.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("ominNevoie"));
        col5nev.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("onSalvator"));
        col6nev.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("status"));
        tabelNevoi.setItems(model);
        col1fap.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("titlu"));
        col2fap.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("descriere"));
        col3fap.setCellValueFactory(new PropertyValueFactory<Nevoie, Date>("deadline"));
        col4fap.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("ominNevoie"));
        col5fap.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("onSalvator"));
        col6fap.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("status"));
        tabelFapte.setItems(model2);

    }

    @FXML
    public void handleSalv(ActionEvent event){
        Nevoie n= (Nevoie) tabelNevoi.getSelectionModel().getSelectedItem();
        int id = n.getId();
        String titlu = n.getTitlu();
        String descr = n.getDescriere();
        LocalDateTime data = n.getDeadline();
        int om_nev = n.getOminNevoie();
        int salv = 0;
        String status = "erou salvator";
        Nevoie nn = new Nevoie(id,titlu,descr,data,om_nev, pers.getId(),status);
        nn.setOnSalvator(pers.getId());
        service.updateNevoie(n);
        //mesaj.setText("Nevoia a fost atribuita!");
        initModelNev();
        initModelFap(pers);

    }

    @FXML
    public void handleCerere(ActionEvent event){
        String titlu=fieldTitluNev.getText();
        String desc=fieldDescNev.getText();
        LocalDateTime dt= pickerNev.getValue().atStartOfDay();
        Random rand = new Random();
        String str="caut erou";
        int upperbound = 10000;
        int x=rand.nextInt(upperbound);
        Nevoie n=new Nevoie(x,titlu,desc,dt,pers.getId(),0,str);
        service.add(n);
        initModelFap(pers);
        initModelNev();


    }


    @Override
    public void update(EntityChangeEvent entityChangeEvent) {

    }
}
