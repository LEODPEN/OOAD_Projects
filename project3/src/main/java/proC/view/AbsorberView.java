package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.AbsorberGizmo;
import proC.models.ObjectsInBoard.Ball;
import proC.utils.Constants;
import proC.utils.Observer;

public class AbsorberView extends Canvas implements Observer {

    //固定左上角坐标
    private final double x;
    private final double y;

    private final AbsorberGizmo absorberModel;
    private final Image image;

    private GraphicsContext gc;


    public AbsorberView(AbsorberGizmo absorberModel) {
        super();
        this.x = absorberModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = absorberModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.image=Constants.ABSORBER_IMAGE;
        this.gc=getGraphicsContext2D();

        this.absorberModel = absorberModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        absorberModel.subscribe(this);
        //初始化view图像
        update();

    }

    @Override
    public void update() {

        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        gc.drawImage(image, 0,0,getWidth(),getHeight());
    }


}
