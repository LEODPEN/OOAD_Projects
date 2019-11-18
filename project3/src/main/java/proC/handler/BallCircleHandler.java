package proC.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import proC.type.ComponentType;

public class BallCircleHandler extends CollisionHandler {
    public BallCircleHandler() {
        super(ComponentType.BALL, ComponentType.CIRCLE);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity circle) {

//        super.onCollisionBegin(ball, circle);
    }
}
