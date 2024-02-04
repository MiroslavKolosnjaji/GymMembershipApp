package frontend.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * @author Miroslav Kološnjaji
 */
public class ControllerEffectsUtil {

    public static void glowEffect(Node node){
        Glow glow = new Glow();
        glow.setLevel(0.2);

        Bloom bloom = new Bloom();
        bloom.setThreshold(0.6);

        Blend blend = new Blend();
        blend.setMode(BlendMode.ADD);
        blend.setTopInput(glow);
        blend.setBottomInput(bloom);

        node.setEffect(blend);
    }


    public static void removeEffect(Node node){
        node.setEffect(null);
    }

    public static void blurEffect(Pane root){
        GaussianBlur gaussianBlur = new GaussianBlur();
        root.getChildren().get(0).setEffect(gaussianBlur);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(gaussianBlur.radiusProperty(),10);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
