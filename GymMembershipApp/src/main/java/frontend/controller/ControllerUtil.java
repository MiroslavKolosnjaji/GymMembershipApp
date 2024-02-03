package frontend.controller;

import backend.database.Database;
import backend.exception.DatabaseException;
import frontend.controller.payment.PaymentFormController;
import frontend.exception.UserInputException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;

/**
 * @author Miroslav Kološnjaji
 */
public class ControllerUtil {

    public static void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void closeApplication(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("no");

        alert.getButtonTypes().setAll(yes, no);

        alert.showAndWait().ifPresent(response -> {
            if (response == yes) {
                try {
                    Database.getInstance().disconnectFromDatabase();
                    Platform.exit();
                } catch (DatabaseException e) {
                    throw new RuntimeException(e);
                }
            } else if (response == no) {
                alert.close();
            }
        });
    }


    public static <T> void createForm(String path, T controller, Pane root, int dimensionX, int dimensionY) {
        try {
            FXMLLoader loader = new FXMLLoader(ControllerUtil.class.getResource(path));
            loader.setController(controller);
            Parent form = loader.load();

            double formX = root.getWidth() / 2;
            double formY = root.getHeight() / 2;

            form.setLayoutX(formX - (dimensionX));
            form.setLayoutY(formY - (dimensionY));

            root.getChildren().add(form);

            ControllerEffectsUtil.blurEffect(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeForm(String title, String headerText, String contentText, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("no");

        alert.getButtonTypes().setAll(yes, no);

        alert.showAndWait().ifPresent(response -> {
            if (response == yes) {
                stage.close();
            } else if (response == no) {
                alert.close();
            }
        });
    }


    public static void validateInput(Map<String, TextField> textFieldMap, String fieldName) throws UserInputException {

        if (textFieldMap.containsKey("phone"))
            validateLength(textFieldMap.get("phone"));

        if (fieldName == null)
            fieldName = "no restrictions";

        if (fieldName == "no restrictions") {
            for (var field : textFieldMap.entrySet())
                if (field.getValue().getText().isEmpty() || field.getValue().getText().isBlank())
                    throw new UserInputException("You didn't filled all fields! Please fill all fields to proceed futher");
        } else {
            for (var field : textFieldMap.entrySet()) {
                if (field.getKey() == fieldName) continue;
                if (field.getValue().getText().isEmpty() || field.getValue().getText().isBlank())
                    throw new UserInputException("Some fields are not filled! Please fill all mandatory fields to proceed futher");
            }
        }
    }

    private static void validateLength(TextField textField) throws UserInputException {
        if (textField.getText().trim().length() != 9)
            throw new UserInputException("Phone number must have 9 digits!");
    }

}
