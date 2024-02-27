package frontend.controller.member;

import server.service.MemberService;
import frontend.controller.ControllerEffectsUtil;
import frontend.controller.member.impl.MemberDataFormControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Map;

public class MemberDataFormController {

    @FXML
    private Pane btnPane;

    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;

    private Stage mainStage;
    private MemberService memberService;

    private Node externalNode;

    public MemberDataFormController(MemberService memberService) {
        this.memberService = memberService;
    }

    public MemberDataFormController(MemberService memberService, Node node) {
        this.memberService = memberService;
        this.externalNode = node;
    }

    @FXML
    void closeForm(MouseEvent event) {
        MemberDataFormControllerImpl.closeWindow(externalNode);
    }


    @FXML
    void confirmInputs(MouseEvent event) {
        Map<String, TextField> map = Map.of("firstName", txtFirstName, "lastName", txtLastName, "phone", txtPhone, "email",  txtEmail);
        MemberDataFormControllerImpl.insert(memberService, map, externalNode);
    }

    @FXML
    void confirmGlowEffect(MouseEvent event){
        ControllerEffectsUtil.glowEffect(btnPane);
    }

    @FXML
    void confirmRemoveGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.removeEffect(btnPane);
    }



}
