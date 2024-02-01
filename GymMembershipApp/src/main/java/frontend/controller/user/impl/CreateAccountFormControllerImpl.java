package frontend.controller.user.impl;

import backend.domain.User;
import backend.service.UserService;
import frontend.controller.ControllerUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Map;

/**
 * @author Miroslav Kološnjaji
 */
public class CreateAccountFormControllerImpl {


    public static void insert(Map<String, TextField> fieldMap, UserService userService) {
        try {
            ControllerUtil.validateInput(fieldMap, null);
            String firstName = fieldMap.get("firstName").getText().trim();
            String lastName = fieldMap.get("lastName").getText().trim();
            String email = fieldMap.get("email").getText().trim();
            String password = fieldMap.get("password").getText().trim();
            String confirmPassword = fieldMap.get("confirmPassword").getText().trim();

            if(!confirmPassword.equals(password)){
                ControllerUtil.showAlert(Alert.AlertType.WARNING,"Warning", "Password confirmation didn't match with your password!");
                return;
            }
            
            userService.add(new User(null,firstName, lastName, null, null, email, password, null));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
