package frontend.controller;

import backend.database.Database;
import backend.exception.DatabaseException;
import frontend.exception.UserInputException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

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

    public static void showConfirmationAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Application will be closed. Do you want to proceed?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("no");

        alert.getButtonTypes().setAll(yes, no);

        alert.showAndWait().ifPresent(response -> {
            if (response == yes) {
                try {
                    Database.getInstance().disconnectFromDatabase();
                    System.exit(0);
                } catch (DatabaseException e) {
                    throw new RuntimeException(e);
                }
            } else if (response == no) {
                alert.close();
            }
        });
    }


    public static void validateInput(Map<String, TextField> textFieldMap, String fieldName) throws UserInputException {

        validateLength(textFieldMap.get("phone"));

        if (fieldName == null)
            fieldName = "no restrictions";

        if (fieldName == "no restrictions") {
            for (var field : textFieldMap.entrySet())
                if (field.getValue().getText().isEmpty() || field.getValue().getText().isBlank())
                    throw new UserInputException("You didn't filled all fields! Please fill all fields to proceed futher");
        } else {
            for (var field : textFieldMap.entrySet()) {
                if(field.getKey() == fieldName) continue;
                if (field.getValue().getText().isEmpty() || field.getValue().getText().isBlank())
                    throw new UserInputException("Some fields are not filled! Please fill all mandatory fields to proceed futher");
            }
        }
    }

    private static void validateLength(TextField textField) throws UserInputException {
        if(textField.getText().trim().length() != 9)
            throw new UserInputException("Phone number must have 9 digits!");
    }

}
