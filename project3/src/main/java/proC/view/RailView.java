package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import proC.models.ObjectsInBoard.RailGizmo;
import proC.models.ObjectsInBoard.SquareGizmo;
import proC.utils.Constants;
import proC.utils.Observer;


public class RailView extends Canvas implements Observer {

    //固定左上角坐标
    private final double x;
    private final double y;

    private final RailGizmo railModel;
    private final Image image;

    private GraphicsContext gc;


    public RailView(RailGizmo railModel) {
        super();
        this.x = railModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = railModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.image=Constants.RAIL_IMAGE;
        this.gc=getGraphicsContext2D();

        this.railModel=railModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        railModel.subscribe(this);
    }

    public void update() {

        //画布位置不变
        //画布大小不变
        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        gc.drawImage(image, 0,0,getWidth(),getHeight());


    }
}
