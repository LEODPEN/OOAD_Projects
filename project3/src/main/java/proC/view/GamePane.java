package proC.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proC.models.ObjectsInBoard.AllObjects;
import proC.models.ObjectsInBoard.Ball;
import proC.models.ObjectsInBoard.TriangleGizmo;
import proC.models.ObjectsInBoard.Walls;
import proC.type.BoardObjectOperationEnum;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;

import java.util.LinkedList;
import java.util.List;

// 纯pane操作,利用坐标
public class GamePane extends Pane {

    AnimationTimer animationTimer;
//    Model model;
    private final Group cells;
    private List<AllObjects> allObjects;

    private AllObjects currentModel;
    private Canvas currentView;



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
        allObjects=new LinkedList<>();
        currentModel=null;
        currentView=null;

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


    public List<AllObjects> getAllObjects() {
        return allObjects;
    }

    public AllObjects getcurrentModel() {
        return currentModel;
    }

    public void setcurrentModel(AllObjects currentModel) {
        this.currentModel = currentModel;
    }

    public Canvas getCurrentView() {
        return currentView;
    }

    public void setCurrentView(Canvas currentView) {
        this.currentView = currentView;
    }

    public void addBallView(Ball ball) {

        BallView ballView=new BallView(ball);

        //新建组件，默认选中
        setcurrentModel(ball);
        setCurrentView(ballView);

        AnimationTimer animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                ballView.update();
            }
        };
        animationTimer.start();

        this.getChildren().add(ballView);
        allObjects.add(ball);
//         窗口置前
//        cells.toFront();
    }

    public void addTriangleView(TriangleGizmo triangleModel){
        TriangleView triangleView=new TriangleView(triangleModel);

        setCurrentView(triangleView);
        setcurrentModel(triangleModel);

        triangleView.update();

        this.getChildren().add(triangleView);
        allObjects.add(triangleModel);

    }

    // 组件全删
    public void removeAll() {
        allObjects.clear();
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
                    selectcurrentModel(x/Constants.BASE_LENGTH_IN_PIXELS,y/Constants.BASE_LENGTH_IN_PIXELS);
                    return;
                case BALL:
//                    image=Constants.BALL_IMAGE;
//                    break;
                    addBallView(new Ball(x/Constants.BASE_LENGTH_IN_PIXELS,y/Constants.BASE_LENGTH_IN_PIXELS,0,0,"ball"));
                    return;
                case TRIANGLE:
                    addTriangleView(new TriangleGizmo(x/Constants.BASE_LENGTH_IN_PIXELS,y/Constants.BASE_LENGTH_IN_PIXELS,1.0,"triangle"));
                    return;
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
                    view.setWidth(Constants.BASE_LENGTH_IN_PIXELS*2);
                    image=Constants.LEFT_PADDLE_IMAGE;
                    break;
                case RIGHT_PADDLE:
                    view.setWidth(Constants.BASE_LENGTH_IN_PIXELS*2);
                    image=Constants.RIGHT_PADDLE_IMAGE;
                    break;
                default:
                    return;
            }

            view.setImage(image);
            this.getChildren().add(view);
            animationTimer.start();
        });

    }

    public void selectcurrentModel(double x,double y){
        for (AllObjects object : getAllObjects()) {
            if(object.getX()==x&&object.getY()==y){
                setcurrentModel(object);
            }
        }

        for (Node child : getChildren()) {
            if(child instanceof Canvas){
                if(child.getLayoutX()==x*Constants.BASE_LENGTH_IN_PIXELS&&child.getLayoutY()==y*Constants.BASE_LENGTH_IN_PIXELS){
                    setCurrentView((Canvas) child);
                }
            }
        }
    }

    public void handleComponentOpertion(BoardObjectOperationEnum operationEnum){

        //没有组件被选中时，不作处理
        if(getcurrentModel()==null)return;

        switch (operationEnum){
            case EXPEND:
                currentModel.expand();
                break;
            case SHRINK:
                currentModel.shrink();
                break;
            case ROTATE:
                currentModel.rotate();
                break;
            case REMOVE:
                getAllObjects().remove(currentModel);
                getChildren().remove(currentView);
                setcurrentModel(null);
                break;
            default:
                return;
        }
    }


}
