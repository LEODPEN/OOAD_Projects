package proC.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import proC.type.ComponentType;

public class BallTriangleHandler extends CollisionHandler {

    public BallTriangleHandler() {
        super(ComponentType.BALL, ComponentType.TRIANGLE);
    }
    //分直角边和斜边，看球此时的方向
    @Override
    protected void onCollisionBegin(Entity ball, Entity triangle) {
        //todo
    }
}
