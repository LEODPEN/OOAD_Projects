package proC.view;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import proC.models.ObjectsInBoard.CirCleGizmo;
import proC.models.ObjectsInBoard.PaddleGizmo;
import proC.type.BoardObjectOperationEnum;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

public class PaddleView extends Canvas implements Observer {

    private final double x;
    private final double y;

    private final PaddleGizmo paddleModel;
    private final Image image;

    private final BoardObjectTypeEnum type;

    private GraphicsContext gc;


    public PaddleView(PaddleGizmo paddleModel) {
        super();
        this.x = paddleModel.getX() * Constants.BASE_LENGTH_IN_PIXELS;
        this.y = paddleModel.getY() * Constants.BASE_LENGTH_IN_PIXELS;
        this.image=Constants.PADDLE_IMAGE;
        this.type=paddleModel.getType();
        this.gc=getGraphicsContext2D();

        this.paddleModel=paddleModel;

        //设置画布位置
        this.setLayoutX(x);
        this.setLayoutY(y);
        //设置画布长宽，限制图片长宽
        this.setWidth(2*Constants.BASE_LENGTH_IN_PIXELS);
        this.setHeight(Constants.BASE_LENGTH_IN_PIXELS);

        paddleModel.subscribe(this);

        update();

    }

    public BoardObjectTypeEnum getType() {
        return type;
    }

    @Override
    public void update() {

        //更新画布位置
        this.setLayoutX(paddleModel.getX() * Constants.BASE_LENGTH_IN_PIXELS);
        this.setLayoutY(paddleModel.getY() * Constants.BASE_LENGTH_IN_PIXELS);

        gc.clearRect(0,0,getWidth(),getHeight());//清空画布
        gc.drawImage(image, 0,0,getWidth(),getHeight());
    }

}
