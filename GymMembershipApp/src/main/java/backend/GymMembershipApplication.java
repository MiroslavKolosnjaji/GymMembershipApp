package backend;

import frontend.controller.login.LoginController;
import backend.repository.UserRepository;
import backend.repository.impl.UserRepositoryImpl;
import backend.service.UserService;
import backend.service.impl.UserServiceImpl;
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

        var location = GymMembershipApplication.class.getResource("/login/login_form.fxml");
        System.out.println(location);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
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