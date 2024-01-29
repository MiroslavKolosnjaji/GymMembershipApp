package frontend.controller.member.impl;

import frontend.controller.ControllerEffectsUtil;
import frontend.controller.ControllerUtil;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Map;

/**
 * @author Miroslav Kološnjaji
 */
public class MemberDataFormControllerImpl {


    public static void insert(Map<String, TextField> textFields, Node externalNode){

        for(var field : textFields.entrySet()){
            if (field.getValue().getText().isEmpty() || field.getValue().getText().isBlank()){
                ControllerUtil.showAlert(Alert.AlertType.WARNING, "Information", "Field" + field.getValue().getId() + " is not filled! Please all fields must be filled.");
                return;
            }
        }

        String firstName = textFields.get("firstName").getText().trim();
        String lastName = textFields.get("lastName").getText().trim();
        String phone = textFields.get("phone").getText().trim();
        String email = textFields.get("email").getText().trim();

        System.out.println(firstName + " " + lastName + " " + phone + " " + email);

        if(externalNode != null)
            closeWindow(externalNode);

    }


    private static void closeWindow(Node node){
        ((AnchorPane)node).getChildren().removeLast();
        ControllerEffectsUtil.removeEffect(((AnchorPane)node).getChildren().get(0));
    }

}
