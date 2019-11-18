package proC.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import proC.type.ComponentType;

public class BallTriangleHandler extends CollisionHandler {

    public BallTriangleHandler() {
        super(ComponentType.BALL, ComponentType.TRIANGLE);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity triangle) {

    }
}
