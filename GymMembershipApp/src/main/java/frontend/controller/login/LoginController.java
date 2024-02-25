package frontend.controller.login;

import backend.exception.InvalidPasswordException;
import backend.exception.RepositoryException;
import backend.exception.UserNotFoundException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import frontend.controller.ControllerEffectsUtil;
import backend.database.Database;
import backend.domain.User;
import backend.exception.DatabaseException;
import backend.service.UserService;
import frontend.controller.ControllerUtil;
import frontend.controller.user.CreateAccountFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;


public class LoginController {

    @FXML
    private AnchorPane root;
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
    @FXML
    private FontAwesomeIcon close;

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
        ControllerEffectsUtil.glowEffect(loginButton);
    }

    @FXML
    protected void loginButtonMouseExited() {
        ControllerEffectsUtil.removeEffect(loginButton);
    }

    @FXML
    protected void createAccountTextMouseEntered() {
        ControllerEffectsUtil.glowEffect(createAccountVBox);
    }

    @FXML
    protected void createAccountTextMouseExited() {
        ControllerEffectsUtil.removeEffect(createAccountVBox);
    }

    @FXML
    protected void loginButtonClicked() {

        String email = usernameTxt.getText();
        String password = passwordTxt.getText();

        if (!isFilled(email)) {
            ControllerUtil.showAlert(Alert.AlertType.INFORMATION, "Information", "Please fill out email field");
            return;
        }

        if (!isFilled(password)) {
            ControllerUtil.showAlert(Alert.AlertType.INFORMATION, "Information", "Please fill out password field");
            return;
        }

        try {
            Optional<User> user = userService.login(new User(email, password));

            if (user.isPresent()) {
                openMainForm();
                System.out.println("User: " + user);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            }

        } catch (UserNotFoundException | InvalidPasswordException | RepositoryException e) {
            ControllerUtil.showAlert(Alert.AlertType.WARNING, "WARNING", e.getMessage());
        }


    }

    @FXML
    void createNewAccount(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/form/user/createAccountForm.fxml"));
            CreateAccountFormController createAccountFormController = new CreateAccountFormController(userService);
            fxmlLoader.setController(createAccountFormController);

            Scene scene = new Scene(fxmlLoader.load());
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            createAccountFormController.setStage(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void openMainForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/form/mainform/main_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.setFill(Color.TRANSPARENT);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Gym Membership");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    void closeGlowEffect(MouseEvent event) {

    }

    @FXML
    void closeRemoveGlowEffect(MouseEvent event) {

    }

    private boolean isFilled(String string) {
        return !string.isEmpty() || !string.isBlank();
    }

}