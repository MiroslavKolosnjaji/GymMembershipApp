package frontend.controller.user;

import backend.service.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import frontend.controller.ControllerEffectsUtil;
import frontend.controller.ControllerUtil;
import frontend.controller.user.impl.CreateAccountFormControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CreateAccountFormController implements Initializable {

    @FXML
    private Button btnConfirm;

    @FXML
    private AnchorPane child;

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private Label lblConfirmPassword;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblPassword;

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    private UserService userService;

    private Stage stage;

    public CreateAccountFormController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    void createAccount(ActionEvent event) {
        Map<String, TextField> fieldMap = Map.of("firstName", txtFirstName, "lastName", txtLastName, "email",txtEmail, "password", txtPassword, "confirmPassword", txtConfirmPassword);
        CreateAccountFormControllerImpl.insert(fieldMap, userService);
    }

    @FXML
    void  closeForm(MouseEvent mouseEvent){
        ControllerUtil.closeForm("Warning","Are you sure?", "", stage);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
