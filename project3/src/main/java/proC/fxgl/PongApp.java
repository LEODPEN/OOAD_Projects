package proC.fxgl;

/**
 * @author onion
 * @date 2019/11/18 -5:40 下午
 */
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PongApp extends GameApplication {

    private static final int PADDLE_WIDTH = 30;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_SPEED = 5;
    private static final int BALL_SPEED = 5;

    private Entity paddle1;
    private Entity paddle2;
    private Entity ball;

    private List<Entity> paddles;
    private ComponentType type;

    private List<Point2D> waypoints = new ArrayList<>();

    public List<Point2D> getWaypoints() {
        return new ArrayList<>(waypoints);
    }

    private Color selectedColor = Color.BLACK;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Pong");
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Up 1") {
            @Override
            protected void onAction() {
                paddle1.translateY(-PADDLE_SPEED);
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Down 1") {
            @Override
            protected void onAction() {
                paddle1.translateY(PADDLE_SPEED);
            }
        }, KeyCode.S);

        input.addAction(new UserAction("Up 2") {
            @Override
            protected void onAction() {
                paddle2.translateY(-PADDLE_SPEED);
            }
        }, KeyCode.UP);

        input.addAction(new UserAction("Down 2") {
            @Override
            protected void onAction() {
                paddle2.translateY(PADDLE_SPEED);
            }
        }, KeyCode.DOWN);
        // test
        paddles = new ArrayList<>();
        input.addAction(new UserAction("Place new paddle") {

            private Rectangle2D worldBounds = new Rectangle2D(0, 0, getAppWidth(), getAppHeight() - 100 - 40);
            @Override
            protected void onAction() {
                if (worldBounds.contains(input.getMousePositionWorld())) {
                    placeTower();
                }
            }
        }, MouseButton.PRIMARY);
    }

    private void placeTower() {
        paddles.add(spawnBat(getInput().getMouseXWorld(),getInput().getMouseYWorld()));
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score1", 0);
        vars.put("score2", 0);
    }

    @Override
    protected void initGame() {
        paddle1 = spawnBat(0, getAppHeight() / 2 - PADDLE_HEIGHT / 2);
        paddle2 = spawnBat(getAppWidth() - PADDLE_WIDTH, getAppHeight() / 2 - PADDLE_HEIGHT / 2);

        ball = spawnBall(getAppWidth() / 2 - BALL_SIZE / 2, getAppHeight() / 2 - BALL_SIZE / 2);
    }

    @Override
    protected void initUI() {
        Text textScore1 = getUIFactory().newText("", Color.BLACK, 22);
        Text textScore2 = getUIFactory().newText("", Color.BLACK, 22);

        textScore1.setTranslateX(10);
        textScore1.setTranslateY(50);

        textScore2.setTranslateX(getAppWidth() - 30);
        textScore2.setTranslateY(50);

        textScore1.textProperty().bind(getGameState().intProperty("score1").asString());
        textScore2.textProperty().bind(getGameState().intProperty("score2").asString());

        getGameScene().addUINodes(textScore1, textScore2);

        // test selector UI
        Rectangle uiBG = new Rectangle(getAppWidth(), 100); // height 100
        uiBG.setTranslateY(500);
        getGameScene().addUINode(uiBG);
        Color color = FXGLMath.randomColor();
//        TowerIcon icon = new TowerIcon(color);
//        icon.setTranslateX(110);
//        icon.setTranslateY(500);
//        icon.setOnMouseClicked(e -> {
//            selectedColor = color;
//        });
//        getGameScene().addUINode(icon);

    }

    @Override
    protected void onUpdate(double tpf) {
        Point2D velocity = ball.getObject("velocity");
        ball.translate(velocity);

        if (ball.getX() == paddle1.getRightX()
                && ball.getY() < paddle1.getBottomY()
                && ball.getBottomY() > paddle1.getY()) {
            ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
        }

        if (ball.getRightX() == paddle2.getX()
                && ball.getY() < paddle2.getBottomY()
                && ball.getBottomY() > paddle2.getY()) {
            ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
        }

        if (ball.getX() <= 0) {
            getGameState().increment("score2", +1);
            resetBall();
        }

        if (ball.getRightX() >= getAppWidth()) {
            getGameState().increment("score1", +1);
            resetBall();
        }

        if (ball.getY() <= 0) {
            ball.setY(0);
            ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
        }

        if (ball.getBottomY() >= getAppHeight()) {
            ball.setY(getAppHeight() - BALL_SIZE);
            ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
        }
    }

//    @Override
//    protected void initPhysics() {
//        getPhysicsWorld().addCollisionHandler(new BulletEnemyHandler());
//    }

    private Entity spawnBat(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .viewWithBBox(new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT))
                .buildAndAttach();
    }

    private Entity spawnBall(double x, double y) {
        return entityBuilder()
                .at(x, y)
                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE))
                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .buildAndAttach();
    }

    private void resetBall() {
        ball.setPosition(getAppWidth() / 2 - BALL_SIZE / 2, getAppHeight() / 2 - BALL_SIZE / 2);
        ball.setProperty("velocity", new Point2D(BALL_SPEED, BALL_SPEED));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
