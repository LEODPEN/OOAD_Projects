package proC.fxgl.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import proC.fxgl.constants.ConfigConstants;
import proC.fxgl.ComponentType;
import proC.fxgl.Direction;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

/**
 * @author onion
 * @date 2019/11/18 -4:26 下午
 */
public class ComponentFactory implements EntityFactory {
    @Spawns("circle")
    public Entity newCircle(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.CIRCLE)
                .view("images.png")
                .collidable()
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .build();
    }

    @Spawns("triangle")
    public Entity newTriangle(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.TRIANGLE)
//                .viewWithBBox(new TriangleMesh())
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
                .with("direction", Direction.LEFT_UP)
                .build();
    }
    @Spawns("square")
    public Entity newSquare(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.SQUARE)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .build();
    }
    @Spawns("rail")
    public Entity newRail(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.RAIL)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
                .with("direction", Direction.VERTICAL)
                .build();
    }
    @Spawns("curve")
    public Entity newCurve(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.CURVE)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .with("direction", Direction.LEFT_UP)
                .collidable()
                .build();
    }
    @Spawns("ball")
    public Entity newBall(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.BALL)
                .viewWithBBox("ball.png")
                .collidable()
                .with("velocity", new Point2D(ConfigConstants.BALL_SPEED, ConfigConstants.BALL_SPEED))
                .build();
    }
    @Spawns("paddle")
    public Entity newPaddle(SpawnData data){
        return entityBuilder()
                .from(data)
                .viewWithBBox(new Rectangle(ConfigConstants.PADDLE_WIDTH, ConfigConstants.PADDLE_HEIGHT))
                .type(ComponentType.PADDLE)
                .collidable()
                .build();
    }
    @Spawns("absorber")
    public Entity newAbsorber(SpawnData data){
        return entityBuilder()
                .from(data)
//                .viewWithBBox(new Rectangle(ConfigConstants.PADDLE_WIDTH, ConfigConstants.PADDLE_HEIGHT))
                .type(ComponentType.ABSORBER)
                .collidable()
                .build();
    }
}
