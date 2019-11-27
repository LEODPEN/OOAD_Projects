package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.SquareGizmo;
import proC.models.ObjectsInBoard.TriangleGizmo;
import proC.utils.Constants;
import proC.utils.Observer;

public class SquareView extends Canvas implements Observer {

    //固定左上角坐标
    private final double x;
    private final double y;

    private final SquareGizmo squareModel;
    private final Image image;

    private GraphicsContext gc;


    public SquareView(SquareGizmo squareModel) {
        super();
        this.x = squareModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = squareModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.image=Constants.SQUARE_IMAGE;
        this.gc=getGraphicsContext2D();

        this.squareModel=squareModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(squareModel.getWidth()*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(squareModel.getWidth()*Constants.BASE_LENGTH_IN_PIXELS);

        squareModel.subscribe(this);

        update();
    }

    public void update() {

        //画布位置不变


        //更新画布大小
        this.setHeight(squareModel.getWidth()*Constants.BASE_LENGTH_IN_PIXELS);
        this.setWidth(squareModel.getWidth()*Constants.BASE_LENGTH_IN_PIXELS);


        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        gc.drawImage(image, 0,0,getWidth(),getHeight());


    }

}
