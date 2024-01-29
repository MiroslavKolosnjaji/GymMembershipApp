module com.example.gymmembership{
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens backend to javafx.fxml;
    exports backend;
    exports frontend.controller;
    opens frontend.controller to javafx.fxml;
    exports frontend.controller.login;
    opens frontend.controller.login to javafx.fxml;
    exports frontend.controller.main;
    opens frontend.controller.main to javafx.fxml;
    exports frontend.controller.member;
    opens  frontend.controller.member to javafx.fxml;
}