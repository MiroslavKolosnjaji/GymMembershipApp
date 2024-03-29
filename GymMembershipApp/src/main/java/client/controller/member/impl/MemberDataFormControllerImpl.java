package client.controller.member.impl;

import server.domain.Member;
import server.service.MemberService;
import client.controller.ControllerEffectsUtil;
import client.controller.ControllerUtil;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Map;

/**
 * @author Miroslav Kološnjaji
 */
public class MemberDataFormControllerImpl {

    public static void insert(MemberService memberService, Map<String, TextField> textFields, Node externalNode){

        try{
            ControllerUtil.validateInput(textFields, null);

            String firstName = textFields.get("firstName").getText().trim();
            String lastName = textFields.get("lastName").getText().trim();
            String phone = textFields.get("phone").getText().trim();
            String email = textFields.get("email").getText().trim();

            System.out.println(firstName + " " + lastName + " " + phone + " " + email);
            memberService.add(new Member(null, firstName, lastName, email, phone, null));

            if(externalNode != null)
                closeWindow(externalNode);

        }catch (Exception uie){
            uie.printStackTrace();
            ControllerUtil.showAlert(Alert.AlertType.WARNING, "Information", uie.getMessage());
        }


    }


    public static void closeWindow(Node node){
        ((AnchorPane)node).getChildren().removeLast();
        ControllerEffectsUtil.removeEffect(((AnchorPane)node).getChildren().get(0));
    }

}
