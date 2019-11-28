package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.RailGizmo;
import proC.models.ObjectsInBoard.SquareGizmo;
import proC.utils.Constants;
import proC.utils.DrawImage;
import proC.utils.Observer;


public class RailView extends Canvas implements Observer {

    //固定左上角坐标
    private final double x;
    private final double y;
    private double angle;

    private final RailGizmo railModel;


    public RailView(RailGizmo railModel) {
        super();
        this.x = railModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = railModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.angle=railModel.getAngle();
        this.railModel=railModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        railModel.subscribe(this);

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
        angle=railModel.getAngle();
        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        DrawImage.drawRotatedImage(gc,Constants.RAIL_IMAGE,angle,0,0,getWidth()/2,getHeight()/2,getWidth(),getHeight());
    }

}
