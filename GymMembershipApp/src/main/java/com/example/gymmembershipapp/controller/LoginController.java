package com.example.gymmembershipapp.controller;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.domain.User;
import com.example.gymmembershipapp.exception.DatabaseException;
import com.example.gymmembershipapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML
    private Button loginButton;
    @FXML
    private VBox createAccountVBox;
    @FXML
    private VBox loginButtonBox;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;

    private UserService userService;

    public LoginController(UserService userService) {
        try {
            Database.getInstance().connectToDatabase();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        this.userService = userService;
    }

    @FXML
    protected void close() {
        System.exit(0);
    }

    @FXML
    protected void loginButtonMouseEntered() {
        onMouseEntered(loginButtonBox);
    }

    @FXML
    protected void loginButtonMouseExited() {
        onMouseExited(loginButtonBox);
    }

    @FXML
    protected void createAccountTextMouseEntered() {
        onMouseEntered(createAccountVBox);
    }

    @FXML
    protected void createAccountTextMouseExited() {
        onMouseExited(createAccountVBox);
    }

    @FXML
    protected void loginButtonClicked() {

        String email = usernameTxt.getText();
        String password = passwordTxt.getText();

        if (!isFilled(email)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Please fill out email field");
            return;
        }

        if (!isFilled(password)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Please fill out password field");
            return;
        }

        try {
            User user = userService.login(new User(email, password));

            if (user != null) {
                openMainForm();
                System.out.println("User: " + user);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            showAlert(Alert.AlertType.WARNING, "WARNING", e.getMessage());
        }


    }

    private void openMainForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/main_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gym Membership");
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void onMouseEntered(Node node) {
        Glow glow = new Glow();
        glow.setLevel(0.2);

        Bloom bloom = new Bloom();
        bloom.setThreshold(0.6);

        Blend blend = new Blend();
        blend.setMode(BlendMode.ADD);
        blend.setTopInput(glow);
        blend.setBottomInput(bloom);

        node.setEffect(blend);
    }

    private void onMouseExited(Node node) {
        node.setEffect(null);
    }

    private boolean isFilled(String string) {
        return !string.isEmpty() || !string.isBlank();
    }
}