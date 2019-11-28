package proC.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import proC.Main;
import proC.models.ObjectsInBoard.Board;
import proC.type.BoardObjectOperationEnum;
import proC.type.BoardObjectTypeEnum;
import proC.type.Mode;
import proC.view.GamePane;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    public Main main;

    @FXML
    private GridPane componentGrid;

    @FXML
    private GridPane operationGrid;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void addClickComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.CLICK);
    }

    @FXML
    public void addBallComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.BALL);
    }

    @FXML
    public void addTriangleComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.TRIANGLE);
    }

    @FXML
    public void addSquareComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.SQUARE);
    }

    @FXML
    public void addAbsorberComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.ABSORBER);
    }

    @FXML
    public void addCircleComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.CIRCLE);
    }

    @FXML
    public void addRailComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.RAIL);
    }

    @FXML
    public void addCurveComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.CURVE);
    }

    @FXML
    public void addLeftPaddleComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.LEFT_PADDLE);
    }

    @FXML
    public void addRightPaddleComponent(){
        main.getGamePane().addComponent(BoardObjectTypeEnum.RIGHT_PADDLE);
    }

    @FXML
    public void expandComponent(){
        main.getGamePane().handleComponentOpertion(BoardObjectOperationEnum.EXPEND);
    }

    @FXML
    public void shrinkComponent(){
        main.getGamePane().handleComponentOpertion(BoardObjectOperationEnum.SHRINK);
    }

    @FXML
    public void rotateComponent(){
        main.getGamePane().handleComponentOpertion(BoardObjectOperationEnum.ROTATE);
    }

    @FXML
    public void removeComponent(){
        main.getGamePane().handleComponentOpertion(BoardObjectOperationEnum.REMOVE);
    }

    @FXML
    public void  changeToConstructMode(){
        //清空组件选择
        main.getGamePane().addComponent(BoardObjectTypeEnum.CLICK);
        main.getGamePane().applyMode(Mode.CONSTRUCT);
        componentGrid.setMouseTransparent(false);
        operationGrid.setMouseTransparent(false);
    }

    @FXML
    public void changeToPlayMode(){
        //清空组件选择
        main.getGamePane().addComponent(BoardObjectTypeEnum.CLICK);
        main.getGamePane().applyMode(Mode.PLAY);
        componentGrid.setMouseTransparent(true);
        operationGrid.setMouseTransparent(true);
    }



}
