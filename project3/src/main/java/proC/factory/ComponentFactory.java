package proC.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.shape.Rectangle;
import proC.components.ExpandComponent;
import proC.components.RotateComponent;
import proC.components.ShrinkComponent;
import proC.constants.ConfigConstants;
import proC.type.ComponentType;

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
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
                .with(new ExpandComponent())
                .with(new ShrinkComponent())
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .build();
    }
    @Spawns("triangle")
    public Entity newTriangle(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.TRIANGLE)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
                .with(new ExpandComponent())
                .with(new ShrinkComponent())
                .with(new RotateComponent())
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .build();
    }
    @Spawns("square")
    public Entity newSquare(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.SQUARE)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
                .with(new ExpandComponent())
                .with(new ShrinkComponent())
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
                .with(new ExpandComponent())
                .with(new ShrinkComponent())
                .with(new RotateComponent())
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .build();
    }
    @Spawns("curve")
    public Entity newCurve(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.CURVE)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
                .with(new ExpandComponent())
                .with(new ShrinkComponent())
                .with(new RotateComponent())
                .build();
    }
    @Spawns("ball")
    public Entity newBall(SpawnData data){
        return FXGL.entityBuilder()
                .from(data)
                .type(ComponentType.BALL)
//                .viewWithBBox(new Rectangle(BALL_SIZE, BALL_SIZE, Color.BLUE))
                .collidable()
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
                .with(new ExpandComponent())
                .with(new ShrinkComponent())
                .build();
    }
    @Spawns("paddle")
    public Entity newPaddle(SpawnData data){
        return entityBuilder()
                .from(data)
                .viewWithBBox(new Rectangle(ConfigConstants.PADDLE_WIDTH, ConfigConstants.PADDLE_HEIGHT))
                .build();
    }
}
