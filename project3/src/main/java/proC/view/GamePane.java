package proC.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import proC.models.ObjectsInBoard.*;
import proC.models.buildAndCollision.Model;
import proC.type.BoardObjectOperationEnum;
import proC.type.BoardObjectTypeEnum;
import proC.type.Mode;
import proC.utils.Constants;
import proC.utils.Observer;

// 纯pane操作,利用坐标
public class GamePane extends Pane {


    private final Model model;
//    private AllObjects currentModel;
    private double mouseX;
    private double mouseY;
    private Canvas currentView;

    private Timeline timeline;
    private PaddleView leftPaddleView;
    private PaddleView rightPaddleView;



    public GamePane() {

        currentView = null;
        model = new Model();
        mouseX = mouseY = -1;
        //创建棋盘视图
        createBoardView();
        //初始化左右挡板视图引用
        leftPaddleView = rightPaddleView = null;

    }


    public void createBoardView(){

        double cellSize = Constants.BASE_LENGTH_IN_PIXELS;
        double width = Constants.BOARD_WIDTH;
        // 20 *20 固定
        this.setPrefSize(cellSize * width, cellSize * width);
        this.setMinSize(cellSize * width, cellSize * width);
        this.setMaxSize(cellSize * width, cellSize * width);

        Group cells = new Group();

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

    }

    public void setMouseXAndMouseY(double x,double y){
        this.mouseX=x;
        this.mouseY=y;
    }

    public Canvas getCurrentView() {
        return currentView;
    }

    public void setCurrentView(Canvas currentView) {
        this.currentView = currentView;
    }


    //设置gamePane的点击事件，用于创建组件
    public void addComponent(BoardObjectTypeEnum type){

        setOnMouseClicked(event->{

            System.out.println("add "+type.toString());
            //获取格点位置
            Double x= Math.floor( event.getSceneX()/Constants.BASE_LENGTH_IN_PIXELS);
            Double y=Math.floor( event.getSceneY()/Constants.BASE_LENGTH_IN_PIXELS)-1;//减去menu的高度

            //更新当前鼠标点击位置
            setMouseXAndMouseY(x,y);

            //单纯点击事件，单独处理
            if(type==BoardObjectTypeEnum.CLICK){
                selectCurrentModelAndView(x,y);
                return;
            }

            //添加组件的视图和模型
            Canvas view;
            Gizmo gizmo=model.addGizmo(x,y,type);

            switch (type){
                case BALL:
                    Ball ball=model.addBall(x,y,0,0);
                    if(ball==null)return;
                    view=new BallView(ball);
                    break;
                case TRIANGLE:
                    view=new TriangleView((TriangleGizmo) gizmo);
                    break;
                case SQUARE:
                    view=new SquareView((SquareGizmo) gizmo);
                    break;
                case ABSORBER:
                    view=new AbsorberView((AbsorberGizmo)gizmo);
                    break;
                case CIRCLE:
                    view=new CircleView((CirCleGizmo)gizmo);
                    break;
                case RAIL:
                    view=new RailView((RailGizmo) gizmo);
                    break;
                case CURVE:
                    view=new CurveView((CurveGizmo)gizmo);
                    break;
                case LEFT_PADDLE:
                    if(gizmo==null||leftPaddleView!=null)return;
                    view=leftPaddleView=new PaddleView((PaddleGizmo)gizmo);
                    break;
                case RIGHT_PADDLE:
                    if(gizmo==null||rightPaddleView!=null)return;
                    view=rightPaddleView=new PaddleView((PaddleGizmo)gizmo);
                    break;
                default:
                    throw new IllegalArgumentException(type.toString());
            }

            setCurrentView(view);
            this.getChildren().add(view);

        });

    }

    public void selectCurrentModelAndView(double x,double y){

        //选中model
        setMouseXAndMouseY(x,y);

        //选中视图
        for (Node child : getChildren()) {
            if(child instanceof Canvas){
                if(child.getLayoutX()==x*Constants.BASE_LENGTH_IN_PIXELS&&child.getLayoutY()==y*Constants.BASE_LENGTH_IN_PIXELS){
                    setCurrentView((Canvas) child);
                }
            }
        }

    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void handleComponentOpertion(BoardObjectOperationEnum operationEnum){

        //没有组件被选中时，不作处理

        double x=getMouseX();
        double y=getMouseY();

        switch (operationEnum){
            case EXPEND:
                model.expandGizmo(x,y);
                break;
            case SHRINK:
                model.shrinkGizmo(x,y);
                break;
            case ROTATE:
                model.rotateGizmo(x,y);
                break;
            case REMOVE:
                model.removeBall(x,y);
                model.removeGizmo(x,y);
                getChildren().remove(currentView);
                break;
            default:
                throw  new IllegalArgumentException("no such operation!");
        }
    }

    public void applyMode(Mode mode){

        switch (mode){
            case PLAY:
                timeline=new Timeline(new KeyFrame(
                        Duration.millis(Constants.MILLIS_PER_FRAME),
                        actionEvent -> model.moveBalls()
                ));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                //设置键盘控制挡板
                setPaddleViewOnKeyPressedEventHandler();
                break;
            case CONSTRUCT:
                timeline.stop();
                //恢复小球、挡板的初始位置(如果存在)
                model.resetBallAndPaddleCoordinate();
                break;
            case STORE:
                //todo
            default:
                return;
        }



    }


    public void setPaddleViewOnKeyPressedEventHandler(){

            //点击游玩模式后，重新获取gamePane焦点
            //点击设计模式后，自动除去焦点
            this.requestFocus();

            this.setOnKeyPressed(event->{
                System.out.println(event.getCode());
                switch (event.getCode()){
                    case A:
                        if(leftPaddleView!=null)
                        model.movePaddle(BoardObjectTypeEnum.LEFT_PADDLE,BoardObjectOperationEnum.MOVE_LEFT);
                        break;
                    case D:
                        if(leftPaddleView!=null)
                        model.movePaddle(BoardObjectTypeEnum.LEFT_PADDLE,BoardObjectOperationEnum.MOVE_RIGHT);
                        break;
                    case J:
                        if(rightPaddleView!=null)
                        model.movePaddle(BoardObjectTypeEnum.RIGHT_PADDLE,BoardObjectOperationEnum.MOVE_LEFT);
                        break;
                    case L:
                        if(rightPaddleView!=null)
                        model.movePaddle(BoardObjectTypeEnum.RIGHT_PADDLE,BoardObjectOperationEnum.MOVE_RIGHT);
                        break;
                    default:
                        System.out.println("fuck");
                        return;
                }
            });
    }


//    public boolean hasOnePaddle(BoardObjectTypeEnum type){
//
//        boolean isLeftPaddle=type==BoardObjectTypeEnum.LEFT_PADDLE;
//        boolean isRightPaddle=type==BoardObjectTypeEnum.RIGHT_PADDLE;
//
//        if(!isLeftPaddle&&!isRightPaddle)return false;
//
//        for (Node child : getChildren()) {
//            if(child instanceof PaddleView){
//                if(((PaddleView) child).getType()==type)
//                    return true;
//            }
//        }
//        return false;
//    }



}
