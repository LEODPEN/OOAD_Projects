package proC.viewController;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proC.Main;

// 纯pane操作,利用坐标
public class GamePane extends Pane {
    AnimationTimer animationTimer;
    Model model;
    public GamePane() {
        var image = new Image(Main.class.getResource("/img/profile.jpg").toExternalForm());
        model = new Model();
        var view = new View(model);
        view.setImage(image);
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                model.update();
                view.update();
            }
        };
        view.widthProperty().bind(widthProperty());
        view.heightProperty().bind(heightProperty());
        this.getChildren().add(view);
    }

    public void start(Stage stage){
        var controller = new GameController(model);
        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, controller);
        stage.getScene().addEventHandler(KeyEvent.KEY_RELEASED, controller);
        animationTimer.start();
    }

    public void stop(){
        animationTimer.stop();
    }


}
