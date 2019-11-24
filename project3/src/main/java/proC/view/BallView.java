package proC.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import proC.models.ObjectsInBoard.Ball;
import proC.utils.Constants;
import proC.utils.Observer;


// todo seems need to do more things here...
public class BallView extends Group implements Observer {

    private final Circle ball;
    private final double x;
    private final double y;
    private final Ball ballModel;

    private boolean selected;

    public BallView(Ball ballModel) {
        super();
        this.x = ballModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = ballModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;

        this.ballModel = ballModel;
        double radius = Constants.BASE_RADIUS_IN_PIXELS;
        ball = new Circle(radius);
        this.getChildren().add(ball);
        // 设置背景图片
        ball.getStyleClass().add("ball");
        this.setTranslateX(x);
        this.setTranslateY(y);
        ballModel.subscribe(this);
        this.getStyleClass().add("ball");

        selected = false;
    }

    @Override
    public void update() {
        this.setTranslateX(ballModel.getX() * Constants.BASE_LENGTH_IN_PIXELS);
        this.setTranslateY(ballModel.getY() * Constants.BASE_LENGTH_IN_PIXELS);
        // 可能大小变化
        this.ball.setRadius(ballModel.getRadius());
        // 窗口暂时置后，不显示？
        this.toBack();
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
