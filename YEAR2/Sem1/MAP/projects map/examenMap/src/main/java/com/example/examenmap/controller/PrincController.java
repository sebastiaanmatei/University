package com.example.examenmap.controller;

import com.example.examenmap.HelloApplication;
import com.example.examenmap.domain.Persoana;
import com.example.examenmap.domain.TipuriOrase;
import com.example.examenmap.service.Service;
import com.example.examenmap.utils.events.EntityChangeEvent;
import com.example.examenmap.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PrincController implements Observer<EntityChangeEvent> {

    Service service;

    ObservableList<Persoana> model = FXCollections.observableArrayList();

    @FXML
    public TextField fieldNume;
    @FXML
    public TextField fieldPrenume;
    @FXML
    public TextField fieldUsername;
    @FXML
    public TextField fieldParola;
    @FXML
    public TextField fieldStrada;
    @FXML
    public TextField fieldNumarStr;
    @FXML
    public TextField fieldTel;

    @FXML
    public TableView tabelUseri;
    @FXML
    public TableColumn col1us;

    @FXML
    public Button butonInreg;

    @FXML
    public ComboBox comboOras;

    @FXML
    public Button butonLog;





    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
        initialize();
        initModel();
    }

    private void initModel() {
        List<Persoana> messages = (List<Persoana>) service.getListPers();
        List<Persoana> sections = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(sections);
    }

    void initialize() {
        col1us.setCellValueFactory(new PropertyValueFactory<Persoana, String>("username"));
        tabelUseri.setItems(model);

        List<String>lls=new ArrayList<>();
        lls.add("Cluj");
        lls.add("Sibiu");
        lls.add("Brasov");

        ObservableList<String> options = FXCollections.observableArrayList(lls);
        comboOras.setItems(options);
    }

    public Stage showPage(Persoana sec) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("nev.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NevController controller = loader.getController();
        controller.setService(service);
        controller.initData(sec);
        stage.show();
        return stage;
    }


    public void handleInreg(ActionEvent event){
        Integer max = 0;
        for(Persoana pers:service.getAllPersoane())
        {
            if(pers.getId()> max)
                max = pers.getId();
        }
        Persoana p = new Persoana(max+1,fieldNume.getText(),fieldPrenume.getText(),fieldUsername.getText(),fieldParola.getText(), (String) comboOras.getSelectionModel().getSelectedItem(),fieldStrada.getText(),fieldNumarStr.getText(),fieldTel.getText());
        service.save(p);
        initModel();
        showPage(p);

    }

    public void handleLogin(ActionEvent event){
        Persoana p= (Persoana) tabelUseri.getSelectionModel().getSelectedItem();
        showPage(p);
    }



    @Override
    public void update(EntityChangeEvent entityChangeEvent) {

    }
}
