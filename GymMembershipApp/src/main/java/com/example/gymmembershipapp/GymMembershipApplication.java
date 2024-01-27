package com.example.gymmembershipapp;

import com.example.gymmembershipapp.controller.LoginController;
import com.example.gymmembershipapp.domain.User;
import com.example.gymmembershipapp.repository.Repository;
import com.example.gymmembershipapp.repository.UserRepository;
import com.example.gymmembershipapp.repository.impl.UserRepositoryImpl;
import com.example.gymmembershipapp.service.UserService;
import com.example.gymmembershipapp.service.impl.UserServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GymMembershipApplication extends Application {

    private UserService userService;
    private UserRepository userRepository;




    @Override
    public void start(Stage stage) throws IOException {

        userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);


        FXMLLoader fxmlLoader = new FXMLLoader(GymMembershipApplication.class.getResource("/login/login_form.fxml"));
        fxmlLoader.setController(new LoginController(userService));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}