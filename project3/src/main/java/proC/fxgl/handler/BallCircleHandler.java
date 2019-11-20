package proC.fxgl.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import proC.fxgl.ComponentType;

public class BallCircleHandler extends CollisionHandler {
    public BallCircleHandler() {
        super(ComponentType.BALL, ComponentType.CIRCLE);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity circle) {
        Point2D velocity = ball.getObject("velocity");
        ball.setProperty("velocity", new Point2D(-velocity.getX(), -velocity.getY()));
    }
}
