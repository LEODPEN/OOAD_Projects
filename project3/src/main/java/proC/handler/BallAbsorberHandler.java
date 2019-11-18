package proC.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import proC.type.ComponentType;

/**
 * @author onion
 * @date 2019/11/18 -7:40 下午
 */
public class BallAbsorberHandler extends CollisionHandler {
    public BallAbsorberHandler() {
        super(ComponentType.BALL, ComponentType.ABSORBER);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity absorber) {
        RemoveHandler.remove(ball);
    }
}
