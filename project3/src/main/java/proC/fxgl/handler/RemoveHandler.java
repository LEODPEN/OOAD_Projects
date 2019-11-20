package proC.fxgl.handler;

import com.almasb.fxgl.entity.Entity;

/**
 * @author onion
 * @date 2019/11/18 -8:03 下午
 */
public class RemoveHandler {
    public static void remove(Entity entity){
        entity.removeFromWorld();
    }
}
