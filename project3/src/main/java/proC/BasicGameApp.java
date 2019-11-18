package proC;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import proC.constants.ConfigConstants;
import proC.factory.ComponentFactory;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BasicGameApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
    }
    private Entity paddle1;
    private Entity paddle2;
    @Override
    protected void initInput() {
        Input input = FXGL.getInput();
        input.addAction(new UserAction("paddle1 move right") {
            @Override
            protected void onAction() {
                paddle1.translateX(5);
                FXGL.getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.D);
        input.addAction(new UserAction("paddle1 move left") {
            @Override
            protected void onAction() {
                paddle1.translateX(-5);
                FXGL.getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.A);
        input.addAction(new UserAction("paddle2 move right") {
            @Override
            protected void onAction() {
                paddle2.translateX(5);
                FXGL.getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.RIGHT);
        input.addAction(new UserAction("paddle2 move left") {
            @Override
            protected void onAction() {
                paddle2.translateX(-5);
                FXGL.getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.LEFT);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    // 相当于挡板
//    private Entity player;

    @Override
    protected void initGame() {
        // set up all stuffs
//       paddle1 = FXGL.entityBuilder()
//                .at(300, 300)
//                // 用的是javafx原生的shape，可以替换为图片
//                .view(new Circle(25, 25, 5,Color.BLUE))
////                .view("brick.png")
//                .buildAndAttach();
        EntityFactory factory = new ComponentFactory();
        FXGL.getGameWorld().addEntityFactory(factory);
        paddle1 = spawn("paddle", 0, getAppHeight() / 2 - ConfigConstants.PADDLE_HEIGHT / 2);
        paddle2 = spawn("paddle", getAppWidth() - ConfigConstants.PADDLE_WIDTH, getAppHeight() / 2 - ConfigConstants.PADDLE_HEIGHT / 2);
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100

        textPixels.textProperty().bind(FXGL.getGameState().intProperty("pixelsMoved").asString());

        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph
    }

    public static void main(String[] args) {
        launch(args);
    }
}
