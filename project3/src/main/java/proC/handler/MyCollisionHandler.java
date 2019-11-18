package proC.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

/**
 * @author onion
 * @date 2019/11/18 -4:46 下午
 */
public class MyCollisionHandler extends CollisionHandler {
    public MyCollisionHandler(Object a, Object b) {
        super(a, b);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
    }
}
