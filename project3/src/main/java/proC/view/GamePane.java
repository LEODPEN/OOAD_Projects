package proC.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proC.models.ObjectsInBoard.Ball;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;

// 纯pane操作,利用坐标
public class GamePane extends Pane {

    AnimationTimer animationTimer;
//    Model model;
    private final Group cells;
    private final Group objects;

    public GamePane() {

        double cellSize = Constants.BASE_LENGTH_IN_PIXELS;
        double width = Constants.BOARD_WIDTH;
        // 20 *20 固定
        this.setPrefSize(cellSize * width, cellSize * width);
        this.setMinSize(cellSize * width, cellSize * width);
        this.setMaxSize(cellSize * width, cellSize * width);

        cells = new Group();

        for (int y = 0; y<20; y++) {
            for (int x = 0; x<20; x++) {
                Pane cell = new Pane();
                cell.getStyleClass().add("grid");
                cell.setPrefSize(cellSize, cellSize);
                cell.setTranslateX(x*cellSize);
                cell.setTranslateY(y*cellSize);
                cells.getChildren().add(cell);
                cells.setMouseTransparent(false);
            }
        }
        this.getChildren().add(cells);

        this.getStyleClass().add("board");

        // all objects
        objects = new Group();
        this.getChildren().add(objects);

//        var image = new Image(Main.class.getResource("/img/profile.jpg").toExternalForm());
//
//        model = new Model();
//        var view = new View(model);
//        view.setImage(image);
//        animationTimer = new AnimationTimer(){
//            @Override
//            public void handle(long now) {
//                model.update();
//                view.update();
//            }
//        };
//        view.widthProperty().bind(widthProperty());
//        view.heightProperty().bind(heightProperty());
//        this.getChildren().add(view);
//        objects.getChildren().add(view);

//        this.getStyleClass().add("board");
//
//        Ball ball = new Ball(5,5,1,1,"test");
//        BallView ballView = new BallView(ball);
//
//        addBallView(ballView);

    }

    public void start(Stage stage){
//        var controller = new GameController(model);
//        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, controller);
//        stage.getScene().addEventHandler(KeyEvent.KEY_RELEASED, controller);
//        animationTimer.start();
    }

//    public void addGizmo(GizmoView gizmoView) {
//        objects.getChildren().add(gizmoView.getNode());
//        cells.toFront();
//    }

    public void addBallView(BallView ballView) {
        objects.getChildren().add(ballView);
        // 窗口置前
        cells.toFront();
    }

    public void removeBallView(BallView ballView) {
        objects.getChildren().remove(ballView);
    }

    // 组件全删
    public void removeAll() {
        objects.getChildren().clear();
    }

    public void stop(){
        animationTimer.stop();
    }

    public void addComponet(BoardObjectTypeEnum type){

        setOnMouseClicked(event->{
            System.out.println("add "+type.toString());
            //获取格点位置
            Double x= Math.floor( event.getSceneX()/Constants.BASE_LENGTH_IN_PIXELS)*Constants.BASE_LENGTH_IN_PIXELS;
            Double y=(Math.floor( event.getSceneY()/Constants.BASE_LENGTH_IN_PIXELS)-1)*Constants.BASE_LENGTH_IN_PIXELS;//减去menu的高度

            Model model = new Model();
            View view = new View(model);
            Image image;

            AnimationTimer animationTimer = new AnimationTimer(){
                @Override
                public void handle(long now) {
                    model.update();
                    view.update();
                }
            };

            //设置图片长宽
            view.setWidth(Constants.BASE_LENGTH_IN_PIXELS);
            view.setHeight(Constants.BASE_LENGTH_IN_PIXELS);
            view.setLayoutX(x);
            view.setLayoutY(y);

            switch (type){
                case CLICK:
                    //todo select component
                    return;
                case BALL:
                    image=Constants.BALL_IMAGE;
                    break;
//                    addBallView(new BallView(new Ball(x,y,0,0,"ball")));
//                    return;
                case TRIANGLE:
                    image=Constants.TRIANGLE_IMAGE;
                    break;
                case SQUARE:
                    image=Constants.SQUARE_IMAGE;
                    break;
                case ABSORBER:
                    image=Constants.ABSORBER_IMAGE;
                    break;
                case CIRCLE:
                    image=Constants.CIRCLE_IMAGE;
                    break;
                case RAIL:
                    image=Constants.RAIL_IMAGE;
                    break;
                case CURVE:
                    image= Constants.CURVE_IMAGE;
                    break;
                case LEFT_PADDLE:
                    if(hasLeftPaddle())return;
                    view.setWidth(Constants.BASE_LENGTH_IN_PIXELS*2);
                    image=Constants.LEFT_PADDLE_IMAGE;
                    break;
                case RIGHT_PADDLE:
                    if(hasRightPaddle())return;
                    view.setWidth(Constants.BASE_LENGTH_IN_PIXELS*2);
                    image=Constants.RIGHT_PADDLE_IMAGE;
                    break;
                default:
                    return;
            }

            view.setImage(image);
            this.getChildren().add(view);
            objects.getChildren().add(view);
            animationTimer.start();
        });

    }


    //判断唯一左挡板
    public boolean hasLeftPaddle(){
        for (Node child : objects.getChildren()) {
            if(child instanceof View){
                if(((View)child).getImage()==Constants.LEFT_PADDLE_IMAGE)
                    return true;
            }
        }
        return false;
    }

    //判断唯一右挡板
    public boolean hasRightPaddle(){
        for (Node child : objects.getChildren()) {
            if(child instanceof View){
                if(((View)child).getImage()==Constants.RIGHT_PADDLE_IMAGE)
                    return true;
            }
        }
        return false;
    }

}
