package proC.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import proC.type.ComponentType;

public class BallSquareHandler extends CollisionHandler {
    public BallSquareHandler() {
        super(ComponentType.BALL, ComponentType.SQUARE);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity square) {

    }
}
