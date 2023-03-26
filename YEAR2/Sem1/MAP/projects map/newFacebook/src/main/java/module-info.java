module com.example.newfacebook {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

//    opens com.example.newfacebook to javafx.fxml;
//    exports com.example.newfacebook;
    exports Facebook;
    opens Facebook to javafx.fxml;
}