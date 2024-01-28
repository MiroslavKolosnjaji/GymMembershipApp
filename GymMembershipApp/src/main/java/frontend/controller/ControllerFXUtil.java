package frontend.controller;

import javafx.scene.Node;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;

/**
 * @author Miroslav Kološnjaji
 */
public class ControllerFXUtil {

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
}
