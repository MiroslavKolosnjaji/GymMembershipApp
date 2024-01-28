package frontend.controller.main;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

    private void populateTable() {
        Node[] nodes = new Node[100];
        for (int i = 0; i < nodes.length; i++) {
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("/frontend/main/item/item.fxml"));
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showStatistics(javafx.scene.input.MouseEvent mouseEvent) {
        GaussianBlur gaussianBlur = new GaussianBlur();
        root.getChildren().get(0).setEffect(gaussianBlur);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(gaussianBlur.radiusProperty(),10);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
