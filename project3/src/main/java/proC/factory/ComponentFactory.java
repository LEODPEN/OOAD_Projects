package proC.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import proC.type.ComponentType;

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
                .with(new CollidableComponent(true))
//                .with("velocity", new Point2D(BALL_SPEED, BALL_SPEED))
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
                .build();
    }
}
