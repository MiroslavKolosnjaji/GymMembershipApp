package frontend.controller.main;


import backend.database.Database;
import backend.exception.DatabaseException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Miroslav Kološnjaji
 */
public class MainController implements Initializable {

    @FXML
    private VBox chartVbox;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTotalLast;

    @FXML
    private VBox pnItems;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane child;

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

    @FXML
    void closeForm(MouseEvent event) {
        ControllerUtil.closeApplication("Confirm dialog", "Are you sure?", "Application will be closed. Do you want to proceed?");
    }

    @FXML
    protected void showStatistics(MouseEvent mouseEvent) {
        ControllerEffectsUtil.blurEffect(root);
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

            root.getChildren().add(memberForm);
            ControllerEffectsUtil.removeEffect(root.getChildren().getLast());
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
