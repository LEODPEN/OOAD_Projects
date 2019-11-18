package proC.components;

import com.almasb.fxgl.entity.component.Component;

/**
 * @author onion
 * @date 2019/11/18 -5:09 下午
 */
public class RotateComponent extends Component {
    @Override
    public void onUpdate(double tpf) {
        entity.rotateBy(90);
    }
}
