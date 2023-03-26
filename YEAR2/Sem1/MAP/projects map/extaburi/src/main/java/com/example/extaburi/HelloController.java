package com.example.extaburi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    public Tab tab1;

    @FXML
    public Tab tab2;

    @FXML
    public Button butonx;
    @FXML
    public Button butony;



    public void handley(){
        System.out.println("buton y");
    }

    public void handleButton(){
        System.out.println("buton x");
    }


}