module com.example.gymmembershipapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.gymmembershipapp to javafx.fxml;
    exports com.example.gymmembershipapp;
    exports frontend.controller;
    opens frontend.controller to javafx.fxml;
    exports frontend.controller.login;
    opens frontend.controller.login to javafx.fxml;
    exports frontend.controller.main;
    opens frontend.controller.main to javafx.fxml;
}