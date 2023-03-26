package com.example.practice2.controller;

import com.example.practice2.domain.Hotel;
import com.example.practice2.domain.SpecialOffer;
import com.example.practice2.service.Service;
import com.example.practice2.utils.events.EntityChangeEvent;
import com.example.practice2.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookingController implements Observer<EntityChangeEvent> {

    Service service;

    Hotel hotel;
    ObservableList<SpecialOffer> model = FXCollections.observableArrayList();

    @FXML
    public TableColumn col2off;

    @FXML
    public TableColumn col3off;

    @FXML
    public TableColumn col4off;

    @FXML
    public TableView tableOffers;

    @FXML
    public DatePicker datePickerHot;

    @FXML
    public DatePicker datePickerEndHot;

    @FXML
    public Button resButton;

    @FXML
    public Button visButton;

    @FXML
    public TextField nameField;


    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
    }

    void initData(Hotel section) {
        this.hotel = section;
        initialize();

    }

    void initialize() {
        col2off.setCellValueFactory(new PropertyValueFactory<SpecialOffer, Date>("startDate"));
        col3off.setCellValueFactory(new PropertyValueFactory<SpecialOffer, Date>("endDate"));
        col4off.setCellValueFactory(new PropertyValueFactory<SpecialOffer, Integer>("percents"));
        tableOffers.setItems(model);


    }

    @FXML
    private void handleDisp(ActionEvent event){
        LocalDate dt=datePickerHot.getValue();
        LocalDate dte=datePickerEndHot.getValue();

        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(dt.atStartOfDay(defaultZoneId).toInstant());
        Date dateEnd = Date.from(dte.atStartOfDay(defaultZoneId).toInstant());

        System.out.println(date);
        System.out.println(dateEnd);
        List<SpecialOffer> messages = service.getOffs();
        List<SpecialOffer> sections = StreamSupport.stream(messages.spliterator(), false)
                .filter(x->(x.startDate.after(date) && x.endDate.before(dateEnd)))
                .collect(Collectors.toList());
        model.setAll(sections);

    }

    @FXML
    private void handleRes(ActionEvent event){
        SpecialOffer sc = (SpecialOffer) tableOffers.getSelectionModel().getSelectedItem();

        //long timeDiff = Math.abs(sc.getEndDate() - sc.getStartDate());
        //long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        //daysBetween(sc.getStartDate(), sc.getEndDate()). getDays());


    }


    @Override
    public void update(EntityChangeEvent entityChangeEvent) {

    }
}
