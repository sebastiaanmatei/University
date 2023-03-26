module com.example.practice2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;


    opens com.example.practice2 to javafx.fxml;
    opens com.example.practice2.domain to javafx.base;
    exports com.example.practice2;
    exports com.example.practice2.controller;
    opens com.example.practice2.controller to javafx.fxml;
}