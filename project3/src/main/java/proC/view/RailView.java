package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.RailGizmo;
import proC.models.ObjectsInBoard.SquareGizmo;
import proC.utils.Constants;
import proC.utils.Observer;


public class RailView extends Canvas implements Observer {

    //固定左上角坐标
    private final double x;
    private final double y;
    private double angle;

    private final RailGizmo railModel;
    private final Image image;

    private GraphicsContext gc;


    public RailView(RailGizmo railModel) {
        super();
        this.x = railModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = railModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.image=Constants.RAIL_IMAGE;
        this.gc=getGraphicsContext2D();
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

    @Override
    public void update() {

        angle=railModel.getAngle();
        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        drawRotatedImage(angle,0,0,getWidth()/2,getHeight()/2,getWidth(),getHeight());
    }


    /**
     //   * @param gc 通过getGraphicsContext2D()获取。
     * @param angle 旋转的角度。
     * @param tlx 旋转之前图片左上角x坐标。
     * @param tly 旋转之前图片左上角y坐标。
     * @param px 旋转中心点x坐标
     * @param py 旋转中心点y坐标
     */
    public void drawRotatedImage(double angle, double tlx, double tly, double px, double py, double width, double height) {
        var rotate = new Rotate();
        gc.save(); //记录当前gc参数
        rotate.setAngle(angle);
        rotate.setPivotX(px);
        rotate.setPivotY(py);
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(),
                rotate.getMyy(), rotate.getTx(), rotate.getTy());
        gc.drawImage(image, tlx, tly, width, height);
        gc.restore(); //恢复gc参数
    }
}
