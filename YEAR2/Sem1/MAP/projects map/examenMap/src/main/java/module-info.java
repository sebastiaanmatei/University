module com.example.examenmap {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.examenmap to javafx.fxml;
    opens com.example.examenmap.domain to javafx.base;
    exports com.example.examenmap;
    exports com.example.examenmap.controller;
    opens com.example.examenmap.controller to javafx.fxml;

}