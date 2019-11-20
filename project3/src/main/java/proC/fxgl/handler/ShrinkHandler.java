package proC.fxgl.handler;

import com.almasb.fxgl.entity.Entity;

/**
 * @author onion
 * @date 2019/11/18 -7:56 下午
 */
public class ShrinkHandler {
    public static void shrink(Entity entity){
        //最小倍数为1
        if(entity.getScaleX() == 1 || entity.getScaleY() == 1)
            return;
        entity.setScaleX(entity.getScaleX() / 2);
        entity.setScaleY(entity.getScaleY() / 2);
    }
}
