package proC.components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class TowerIcon extends Pane {

    public TowerIcon(Color color) {

        getChildren().add(new Rectangle(80, 80, color));
    }
}
