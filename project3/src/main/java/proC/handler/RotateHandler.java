package proC.handler;

import com.almasb.fxgl.entity.Entity;
import proC.type.Direction;

/**
 * @author onion
 * @date 2019/11/18 -8:02 下午
 */
public class RotateHandler {
    public static void rotate(Entity entity){
        entity.rotateBy(90);
        Direction dir = entity.getObject("direction");
        //可以用工厂方法再封装一下
        if (entity.getType().toString().equals("RAIL")) {
            entity.setProperty("direction", dir == Direction.HORIZONTAL ? Direction.VERTICAL : Direction.HORIZONTAL);
        }else{
            Direction[] dirs = {Direction.LEFT_UP, Direction.RIGHT_DOWN, Direction.RIGHT_UP, Direction.LEFT_DOWN};
            for (int i = 0; i < dirs.length; i++) {
                if (dirs[i] == dir){
                    entity.setProperty("direction", dirs[(i+1)%4]);
                }
            }
        }
    }
}
