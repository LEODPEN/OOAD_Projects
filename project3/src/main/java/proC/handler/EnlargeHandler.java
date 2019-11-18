package proC.handler;

import com.almasb.fxgl.entity.Entity;

/**
 * @author onion
 * @date 2019/11/18 -7:55 下午
 */
public class EnlargeHandler {
    public static void enlarge(Entity entity){
        //放大倍数不能超过4
        if(entity.getScaleX() > 4 || entity.getScaleY() > 4)
            return;
        entity.setScaleX(entity.getScaleX() * 2);
        entity.setScaleY(entity.getScaleY() * 2);
    }
}
