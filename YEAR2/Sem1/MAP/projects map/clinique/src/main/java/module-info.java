module com.example.clinique {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.clinique to javafx.fxml;
    opens com.example.clinique.domain to javafx.base;
    exports com.example.clinique;
    exports com.example.clinique.controller;
    opens com.example.clinique.controller to javafx.fxml;

}