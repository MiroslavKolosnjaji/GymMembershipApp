module com.example.gymmembership{
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires fontawesomefx;
    requires de.svws_nrw.external.jbcrypt;

    opens server to javafx.fxml;
    exports server;
    exports client.controller;
    opens client.controller to javafx.fxml;
    exports client.controller.login;
    opens client.controller.login to javafx.fxml;
    exports client.controller.main;
    opens client.controller.main to javafx.fxml;
    exports client.controller.member;
    opens  client.controller.member to javafx.fxml;
    exports client.controller.statistics;
    opens client.controller.statistics to javafx.fxml;
    exports client.controller.user;
    opens client.controller.user to javafx.fxml;
    exports client.controller.payment;
    opens client.controller.payment to javafx.fxml;



}