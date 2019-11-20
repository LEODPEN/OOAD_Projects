package proC.fxgl.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import proC.fxgl.ComponentType;

/**
 * @author onion
 * @date 2019/11/18 -7:37 下午
 */
public class BallRailHandler extends CollisionHandler {
    public BallRailHandler() {
        super(ComponentType.BALL, ComponentType.RAIL);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity rail) {
        Point2D point2D = ball.getObject("velocity");
        ball.setProperty("velocity", point2D);
//        super.onCollisionBegin(ball, circle);
    }

}
