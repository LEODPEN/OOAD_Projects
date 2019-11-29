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

    public BallView(Ball ballModel) {
        super();
        this.x = ballModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = ballModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.ballModel = ballModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        ballModel.subscribe(this);

        update();
    }

    @Override
    public void update() {
        System.out.println("update ball");

        GraphicsContext gc=getGraphicsContext2D();
        //更新画布位置
        this.setLayoutX(ballModel.getX() * Constants.BASE_LENGTH_IN_PIXELS);
        this.setLayoutY(ballModel.getY() * Constants.BASE_LENGTH_IN_PIXELS);

        //更新画布大小
        this.setWidth(ballModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(ballModel.getRadius()*2*Constants.BASE_LENGTH_IN_PIXELS);


        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        if(ballModel.isAbsorbed())return;
        gc.drawImage(Constants.BALL_IMAGE, 0,0,getWidth(),getHeight());
    }

}
