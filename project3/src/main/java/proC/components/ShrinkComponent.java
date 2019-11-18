package proC.components;

import com.almasb.fxgl.entity.component.Component;
import proC.constants.ConfigConstants;

/**
 * @author onion
 * @date 2019/11/18 -5:16 下午
 */
public class ShrinkComponent extends Component {
    @Override
    public void onUpdate(double tpf) {
        if (entity.getScaleX() <= ConfigConstants.MIN_SCALE_X
                || entity.getScaleY() <= ConfigConstants.MIN_SCALE_Y)
            return;
        entity.setScaleX(entity.getScaleX() * 2);
        entity.setScaleY(entity.getScaleY() * 2);
    }
}
