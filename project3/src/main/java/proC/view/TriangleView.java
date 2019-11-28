package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.Ball;
import proC.models.ObjectsInBoard.TriangleGizmo;
import proC.physicsWorld.Vect;
import proC.utils.Constants;
import proC.utils.DrawImage;
import proC.utils.Observer;

public class TriangleView extends Canvas implements Observer {


    //固定左上角坐标
    private final double x;
    private final double y;

    //当前画布角度
    private double angle;

    private final TriangleGizmo triangleModel;

    public TriangleView(TriangleGizmo triangleModel) {
        super();
        this.x = triangleModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = triangleModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.angle=triangleModel.getAngle();

        this.triangleModel = triangleModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(triangleModel.getSide()*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(triangleModel.getSide()*Constants.BASE_LENGTH_IN_PIXELS);

        triangleModel.subscribe(this);

        update();
    }

    public void update() {

        //画布位置不变
        this.setLayoutX(x);
        this.setLayoutY(y);

        //更新画布大小
        this.setHeight(triangleModel.getSide()*Constants.BASE_LENGTH_IN_PIXELS);
        this.setWidth(triangleModel.getSide()*Constants.BASE_LENGTH_IN_PIXELS);

        GraphicsContext gc=getGraphicsContext2D();
        angle=triangleModel.getAngle();
        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        DrawImage.drawRotatedImage(gc,Constants.TRIANGLE_IMAGE,angle,0,0,getWidth()/2,getHeight()/2,getWidth(),getHeight());

    }


}
