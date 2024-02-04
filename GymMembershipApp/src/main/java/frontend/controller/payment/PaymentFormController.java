package frontend.controller.payment;

import backend.domain.Member;
import backend.service.MemberService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PaymentFormController implements Initializable {

    @FXML
    private Button btnConfirm;

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private ComboBox<String> comboMembership;

    @FXML
    private ComboBox<Member> comboMember;

    @FXML
    private DatePicker datePckrDateFrom;

    @FXML
    private DatePicker datePckrDateTo;

    @FXML
    private Label lblDateFrom;

    @FXML
    private Label lblDateTo;

    @FXML
    private Label lblMember;

    @FXML
    private Label lblMemberSHip;

    private MemberService memberService;
    private List<Member> memberList;

    public PaymentFormController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateComboAndSearchList();
        datePckrDateFrom.setValue(LocalDate.now());
        datePckrDateTo.setValue(LocalDate.now());
    }


    @FXML
    void confirmation(ActionEvent event) {

        String membership = comboMembership.getValue();


    }

    @FXML
    void memberSearch(KeyEvent event) {
        String input = String.valueOf(comboMember.getEditor().textProperty());
        System.out.println(input);
        List<Member> members = memberList.stream().filter(m -> m.getFirstName().contains(input)).collect(Collectors.toList());
        populateBySearchCriterium(members);
    }


    private void populateComboAndSearchList() {
        try {
            memberList = memberService.getAll();

            comboMember.setItems(FXCollections.observableList(memberList));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void populateBySearchCriterium(List<Member> members) {
        comboMember.setItems(FXCollections.observableList(members));
    }
}
