package proC.fxgl;

import com.almasb.fxgl.app.*;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import proC.fxgl.constants.ConfigConstants;
import proC.fxgl.factory.ComponentFactory;
import proC.fxgl.handler.EnlargeHandler;
import proC.fxgl.ui.ComponentPane;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;


/**
 * 1.重力加速度未考虑
 * 2.碰撞未测试
 * 3.边界处理未实现
 * 4.布局模式、文件模式未实现
 * 5.其他还没想到的
 */
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
    private Entity ball;

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
        EntityFactory factory = new ComponentFactory();
        FXGL.getGameWorld().addEntityFactory(factory);
        paddle1 = spawn("paddle", 0, getAppHeight() / 2 - ConfigConstants.PADDLE_HEIGHT / 2 + 100);
        paddle2 = spawn("paddle", getAppWidth() - ConfigConstants.PADDLE_WIDTH, getAppHeight() / 2 - ConfigConstants.PADDLE_HEIGHT / 2 + 100);
        ball = spawn("ball", getAppWidth() / 2 - ConfigConstants.BALL_SIZE / 2, getAppHeight() / 2 - ConfigConstants.BALL_SIZE / 2);
        System.out.println(paddle1.getType());
        System.out.println(ball.getType());
    }
    public List<Entity> getCurrentEntity(Point2D position){
        return getGameWorld().getEntitiesAt(position);
    }
    @Override
    protected void initPhysics() {
        //以下为测试
        onCollisionBegin(ComponentType.BALL, ComponentType.PADDLE, (ball, paddle) -> {
            System.out.println("collide");
            Point2D velocity = ball.getObject("velocity");
            if (FXGLMath.randomBoolean()) {
                ball.setProperty("velocity", new Point2D(-velocity.getX(), velocity.getY()));
                EnlargeHandler.enlarge(ball);
            } else {
                ball.setProperty("velocity", new Point2D(velocity.getX(), -velocity.getY()));
//                RotateHandler.rotate(paddle);
                EnlargeHandler.enlarge(ball);
            }
        });
        //以下为后续扩展的代码：存在的问题，发生碰撞后能否定位到应该处理碰撞的handler？（通过参数类型匹配）
//        PhysicsWorld physicsWorld = getPhysicsWorld();
//        physicsWorld.addCollisionHandler(new BallCircleHandler());
//        physicsWorld.addCollisionHandler(new BallSquareHandler());
//        physicsWorld.addCollisionHandler(new BallTriangleHandler());
//        physicsWorld.addCollisionHandler(new BallRailHandler());
//        physicsWorld.addCollisionHandler(new BallCurveHandler());
//        physicsWorld.addCollisionHandler(new BallAbsorberHandler());
    }

    @Override
    protected void onUpdate(double tpf) {
        Point2D velocity = ball.getObject("velocity");
        ball.translate(velocity);
    }

    @Override
    protected void initUI() {
        ComponentPane componentPane=new ComponentPane();

        addUINode(componentPane);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
