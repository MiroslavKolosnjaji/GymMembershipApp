package frontend.controller;

import javafx.scene.control.Alert;

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

}
