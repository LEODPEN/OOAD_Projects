package proC.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import proC.models.ObjectsInBoard.*;
import proC.models.buildAndCollision.Model;
import proC.type.BoardObjectOperationEnum;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

// 纯pane操作,利用坐标
public class GamePane extends Pane {


    private final Model model;
//    private AllObjects currentModel;
    private double mouseX;
    private double mouseY;
    private Canvas currentView;



    public GamePane() {

        currentView=null;
        model=new Model();
        mouseX=mouseY=-1;
        //创建棋盘视图
        createBoardView();
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


    //初始化view并加入棋盘
    public void addView(Canvas view){
        setCurrentView(view);
        ((Observer)view).update();
        this.getChildren().add(view);
    }

    public void addComponet(BoardObjectTypeEnum type){

        setOnMouseClicked(event->{

            System.out.println("add "+type.toString());
            //获取格点位置
            Double x= Math.floor( event.getSceneX()/Constants.BASE_LENGTH_IN_PIXELS);
            Double y=Math.floor( event.getSceneY()/Constants.BASE_LENGTH_IN_PIXELS)-1;//减去menu的高度

            //更新当前鼠标点击位置
            setMouseXAndMouseY(x,y);

            //添加组件的视图和模型
            Canvas view;
            Gizmo gizmo=model.addGizmo(x,y,type);

            switch (type){
                case CLICK:
                    selectcurrentModel(x,y);
                    return;
                case BALL:
                    view=new BallView(model.addBall(x,y,0,0));
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
                case RIGHT_PADDLE:
                    view=new PaddleView((PaddleGizmo)gizmo);
                    break;
                default:
                    System.out.println("no such type");
                    return;
            }

            addView(view);

        });

    }

    public void selectcurrentModel(double x,double y){

        //选中model
        setMouseXAndMouseY(x,y);

        //选中视图
        for (Node child : getChildren()) {
            if(child instanceof Canvas){
                if(child.getLayoutX()==x*Constants.BASE_LENGTH_IN_PIXELS&&child.getLayoutY()==y*Constants.BASE_LENGTH_IN_PIXELS){
                    setCurrentView((Canvas) child);
                    return;
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
                System.out.println("no such operation!");
                return;
        }
    }


}
