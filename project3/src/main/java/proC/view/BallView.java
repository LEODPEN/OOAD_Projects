package proC.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import proC.models.ObjectsInBoard.Ball;
import proC.utils.Constants;
import proC.utils.Observer;


// todo seems need to do more things here...
public class BallView extends Canvas implements Observer {

    //固定左上角坐标
    private final double x;
    private final double y;

    private final Ball ballModel;
    private final Image image;

    private GraphicsContext gc;


    public BallView(Ball ballModel) {
        super();
        this.x = ballModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = ballModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.image=Constants.BALL_IMAGE;
        this.gc=getGraphicsContext2D();

        this.ballModel = ballModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        ballModel.subscribe(this);
    }

    public void update() {

        //更新画布位置
        this.setLayoutX(ballModel.getX() * Constants.BASE_LENGTH_IN_PIXELS);
        this.setLayoutY(ballModel.getY() * Constants.BASE_LENGTH_IN_PIXELS);

        //更新画布大小
        this.setWidth(ballModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(ballModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);

        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        gc.drawImage(image, 0,0,getWidth(),getHeight());
    }

    //绘制水平翻转后的图像
    public void flipDraw(double x, double y){
        gc.save();
        gc.scale(-1,1);//-1,1表示水平翻转，1,-1表示垂直翻转
        gc.drawImage(image,x,y);
        gc.restore(); }
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