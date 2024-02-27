package frontend.controller.main;

import server.repository.CityRepository;
import server.repository.GymRepository;
import server.repository.MemberRepository;
import server.repository.UserRepository;
import server.repository.impl.CityRepositoryImpl;
import server.repository.impl.GymRepositoryImpl;
import server.repository.impl.MemberRepositoryImpl;
import server.repository.impl.UserRepositoryImpl;
import server.service.CityService;
import server.service.GymService;
import server.service.MemberService;
import server.service.UserService;
import server.service.impl.CityServiceImpl;
import server.service.impl.GymServiceImpl;
import server.service.impl.MemberServiceImpl;
import server.service.impl.UserServiceImpl;
import server.util.PasswordManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import frontend.controller.ControllerEffectsUtil;
import frontend.controller.ControllerUtil;
import frontend.controller.member.MemberDataFormController;
import frontend.controller.payment.PaymentFormController;
import frontend.controller.statistics.StatisticsFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Miroslav Kološnjaji
 */
public class MainController implements Initializable {

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private FontAwesomeIcon expand;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTotalLast;

    @FXML
    private ComboBox<String> listCombo;

    @FXML
    private FontAwesomeIcon logout;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private VBox pnItems;

    @FXML
    private AnchorPane root;

    @FXML
    private Pane search_pane;

    @FXML
    private HBox tableHeader;

    @FXML
    private ScrollPane tableScrollPane;

    @FXML
    private Text txtCreate;

    @FXML
    private Text txtDelete;

    @FXML
    private Text txtPayment;

    @FXML
    private TextField txtSearchField;

    @FXML
    private Text txtStatistics;

    @FXML
    private Text txtUpdate;

    private CityService cityService;
    private GymService gymService;
    private UserService userService;
    private MemberService memberService;
    private PasswordManager passwordManager;


    public MainController() {

        CityRepository cityRepository = new CityRepositoryImpl();
        this.cityService = new CityServiceImpl(cityRepository);

        GymRepository gymRepository = new GymRepositoryImpl();
        this.gymService = new GymServiceImpl(gymRepository);

        UserRepository userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository, passwordManager);

        MemberRepository memberRepository = new MemberRepositoryImpl();
        this.memberService = new MemberServiceImpl(memberRepository);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
        populateCombo();
        passwordManager = new PasswordManager();
    }


    private void populateTable() {
        List<Node> nodes = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            try {
                nodes.add(FXMLLoader.load(getClass().getResource("/form/mainform/item/item.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        nodes.forEach(n -> pnItems.getChildren().add(n));
    }

    private void populateCombo() {
        List.of("DEFAULT", "YEAR", "6 MONTHS", "MONTH").forEach(c -> listCombo.getItems().add(c));
        listCombo.getStylesheets().add("combo-color");
    }

    @FXML
    void closeForm(MouseEvent event) {
        ControllerUtil.closeApplication("Confirm dialog", "Are you sure?", "Application will be closed. Do you want to proceed?");
    }

    @FXML
    void showStatistics(MouseEvent mouseEvent) {
        try {
            ControllerEffectsUtil.blurEffect(root);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/form/statistics/statisticsForm.fxml"));
            loader.setController(new StatisticsFormController());

            Parent statistics = loader.load();
            root.getChildren().add(statistics);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void createNew(MouseEvent mouseEvent) {
        ControllerUtil.createForm("/form/member/memberDataForm.fxml", new MemberDataFormController(memberService, root), root, 300, 300);
    }

    @FXML
    void newPayment(MouseEvent event) {
        ControllerUtil.createForm("/form/payment/paymentForm.fxml", new PaymentFormController(memberService), root, 400, 200);
    }

    @FXML
    void createNewGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.glowEffect(txtCreate);
    }

    @FXML
    void createNewRemoveGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.removeEffect(txtCreate);
    }

    @FXML
    void newPaymentGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.glowEffect(txtPayment);
    }

    @FXML
    void newPaymentRemoveGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.removeEffect(txtPayment);
    }
}
