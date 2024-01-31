package frontend.controller.main;

import backend.repository.CityRepository;
import backend.repository.GymRepository;
import backend.repository.MemberRepository;
import backend.repository.UserRepository;
import backend.repository.impl.CityRepositoryImpl;
import backend.repository.impl.GymRepositoryImpl;
import backend.repository.impl.MemberRepositoryImpl;
import backend.repository.impl.UserRepositoryImpl;
import backend.service.CityService;
import backend.service.GymService;
import backend.service.MemberService;
import backend.service.UserService;
import backend.service.impl.CityServiceImpl;
import backend.service.impl.GymServiceImpl;
import backend.service.impl.MemberServiceImpl;
import backend.service.impl.UserServiceImpl;
import frontend.controller.ControllerEffectsUtil;
import frontend.controller.ControllerUtil;
import frontend.controller.member.MemberDataFormController;
import frontend.controller.statistics.StatisticsFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
    private CityRepository cityRepository;
    private GymRepository gymRepository;
    private UserRepository userRepository;
    private MemberRepository memberRepository;


    public MainController() {

        this.cityRepository = new CityRepositoryImpl();
        this.cityService = new CityServiceImpl(cityRepository);

        this.gymRepository = new GymRepositoryImpl();
        this.gymService = new GymServiceImpl(gymRepository);

        this.userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);

        this.memberRepository = new MemberRepositoryImpl();
        this.memberService = new MemberServiceImpl(memberRepository);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
        populateCombo();
    }


    private void populateTable() {
        Node[] nodes = new Node[100];
        for (int i = 0; i < nodes.length; i++) {
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("/mainform/item/item.fxml"));
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void populateCombo(){
        List.of("DEFAULT", "YEAR", "6 MONTHS", "MONTH").forEach(c -> listCombo.getItems().add(c));
        listCombo.setStyle(".combo-color");
    }

    @FXML
    void closeForm(MouseEvent event) {
        ControllerUtil.closeApplication("Confirm dialog", "Are you sure?", "Application will be closed. Do you want to proceed?");
    }

    @FXML
    protected void showStatistics(MouseEvent mouseEvent) {
        try {
        ControllerEffectsUtil.blurEffect(root);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/statistics/statisticsForm.fxml"));
        loader.setController(new StatisticsFormController());

            Parent statistics = loader.load();
            root.getChildren().add(statistics);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    protected void createNew(MouseEvent mouseEvent) {
        try {
            ControllerEffectsUtil.blurEffect(root);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/member/memberDataForm.fxml"));
            loader.setController(new MemberDataFormController(memberService, root));
            Parent memberForm = loader.load();
            double formX = root.getWidth() / 2;
            double formY = root.getHeight() / 2;

            memberForm.setLayoutX(formX - (300));
            memberForm.setLayoutY(formY - (300));

            BoxBlur blur = new BoxBlur(15,15, 5);
            root.getChildren().add(memberForm);
            root.getChildren().getLast().setEffect(blur);

            Scene scene = new Scene(root, 600, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    protected void createNewGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.glowEffect(txtCreate);
    }

    @FXML
    protected void createNewRemoveGlowEffect(MouseEvent event) {
        ControllerEffectsUtil.removeEffect(txtCreate);
    }
}
