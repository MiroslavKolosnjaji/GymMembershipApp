package frontend.controller;

import frontend.exception.UserInputException;
import javafx.scene.control.Alert;
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
