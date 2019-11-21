package proC.viewController;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proC.Main;
import proC.utils.Constants;

// 纯pane操作,利用坐标
public class GamePane extends Pane {

    AnimationTimer animationTimer;
    Model model;
    private final Group cells;

    public GamePane() {
        double cellSize = Constants.BASE_LENGTH_IN_PIXELS;
        double width = Constants.BOARD_WIDTH;
        // 20 *20 固定
        this.setPrefSize(cellSize * width, cellSize * width);
        this.setMinSize(cellSize * width, cellSize * width);
        this.setMaxSize(cellSize * width, cellSize * width);

        cells = new Group();

        for (int y = 0; y<20; y++) {
            for (int x = 0; x<20; x++) {
                Pane cell = new Pane();
                cell.getStyleClass().add("grid");
                cell.setPrefSize(cellSize, cellSize);
                cell.setTranslateX(x*cellSize);
                cell.setTranslateY(y*cellSize);
                cells.getChildren().add(cell);
                cells.setMouseTransparent(false);
            }
        }
        this.getChildren().add(cells);

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
