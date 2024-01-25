module com.example.gymmembershipapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.gymmembershipapp to javafx.fxml;
    exports com.example.gymmembershipapp;
}