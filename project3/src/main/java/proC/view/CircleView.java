package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.Ball;
import proC.models.ObjectsInBoard.CirCleGizmo;
import proC.utils.Constants;
import proC.utils.Observer;

public class CircleView extends Canvas implements Observer {

    private final double x;
    private final double y;

    private final CirCleGizmo cirCleModel;


    public CircleView(CirCleGizmo cirCleModel) {
        super();
        this.x = cirCleModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = cirCleModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;

        this.cirCleModel = cirCleModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(cirCleModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(cirCleModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);

        cirCleModel.subscribe(this);

        update();

    }

    //读取文件时，重新设置Node（无法序列化）相关属性
    @Override
    public void update() {

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        GraphicsContext gc=getGraphicsContext2D();
        //更新画布大小
        this.setWidth(cirCleModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(cirCleModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);

        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        gc.drawImage(Constants.CIRCLE_IMAGE, 0,0,getWidth(),getHeight());
    }


}
