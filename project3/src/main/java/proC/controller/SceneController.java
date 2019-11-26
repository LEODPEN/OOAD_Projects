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

    private GamePane gamePane;

    @FXML
    private GridPane componentGrid;

    @FXML
    private GridPane operationGrid;

    public void setMain(Main main) {

        this.main = main;
        this.gamePane=main.getGamePane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void addClickComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.CLICK);
    }

    @FXML
    public void addBallComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.BALL);
    }

    @FXML
    public void addTriangleComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.TRIANGLE);
    }

    @FXML
    public void addSquareComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.SQUARE);
    }

    @FXML
    public void addAbsorberComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.ABSORBER);
    }

    @FXML
    public void addCircleComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.CIRCLE);
    }

    @FXML
    public void addRailComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.RAIL);
    }

    @FXML
    public void addCurveComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.CURVE);
    }

    @FXML
    public void addLeftPaddleComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.LEFT_PADDLE);
    }

    @FXML
    public void addRightPaddleComponent(){
        gamePane.addComponet(BoardObjectTypeEnum.RIGHT_PADDLE);
    }

    @FXML
    public void expandComponent(){
        gamePane.handleComponentOpertion(BoardObjectOperationEnum.EXPEND);
    }

    @FXML
    public void shrinkComponent(){
        gamePane.handleComponentOpertion(BoardObjectOperationEnum.SHRINK);
    }

    @FXML
    public void rotateComponent(){
        gamePane.handleComponentOpertion(BoardObjectOperationEnum.ROTATE);
    }

    @FXML
    public void removeComponent(){
        gamePane.handleComponentOpertion(BoardObjectOperationEnum.REMOVE);
    }

    @FXML
    public void  changeToConstructMode(){
        //清空组件选择
        gamePane.addComponet(BoardObjectTypeEnum.CLICK);
        gamePane.applyMode(Mode.CONSTRUCT);
        componentGrid.setMouseTransparent(false);
        operationGrid.setMouseTransparent(false);
    }

    @FXML
    public void changeToPlayMode(){
        //清空组件选择
        gamePane.addComponet(BoardObjectTypeEnum.CLICK);
        gamePane.applyMode(Mode.PLAY);
        componentGrid.setMouseTransparent(true);
        operationGrid.setMouseTransparent(true);
    }



}
