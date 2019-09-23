package proA.viewAndController;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;
import proA.Main;
import proA.game.PositionInfo;

import java.net.URL;
import java.util.*;

/**
 * @author timgin
 * @date 2019/9/17 15:25
 */
public class AntGameController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button play;

    @FXML
    private Button stop;

    private Rectangle stick;

    //stick的x轴位置
    private static final double STICK_X=20d;

    //stick的y轴位置
    private static final double STICK_Y=300d;

    //stick的高度
    private static final double STICK_HEIGHT=10d;

    private static final Color STICK_FILL=Color.GOLDENROD;

    private Timeline minTimeLine;

    private Timeline maxTimeLine;

    private ImageView antView;

    private Circle ant;

    public Main main;

    public AntGameController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        createStick();
        createAnts();
//        initTransition();
    }

    private void createStick(){
        System.out.println("creating a stick");
        stick=new Rectangle(500, STICK_HEIGHT,STICK_FILL);
        stick.setX(STICK_X);
        stick.setY(STICK_Y);
        root.getChildren().add(stick);
        System.out.println("stick created");
    }

    private void createAnts(){

        antView=new ImageView("file:///C:/Users/74467/Desktop/work/OOAD_Projects/projects/src/proA/resources/images/ant.png");
        antView.setX(20d);
        antView.setY(275d);
        root.getChildren().add(antView);
//        ant=new Circle(20d,300d,10d);
//        root.getChildren().add(ant);

    }

    public void initTransition(){

        maxTimeLine=new Timeline();
        maxTimeLine.setCycleCount(Timeline.INDEFINITE);
        maxTimeLine.setAutoReverse(true);

//        System.out.println("???");
        ArrayList<PositionInfo>[][] traceList= main.getTraceList();
        int minState=main.getMinState();
        int maxState=main.getMaxState();


        KeyValue kv=new KeyValue(antView.xProperty(),300);
        KeyFrame kf=new KeyFrame(Duration.millis(1000),kv);

        KeyValue kv2=new KeyValue(antView.xProperty(),100);
        KeyFrame kf2=new KeyFrame(Duration.millis(1000),kv2);

        maxTimeLine.getKeyFrames().addAll(kf,kf2);
        maxTimeLine.play();
    }

    @FXML
    public void play(){
        System.out.println("Enter play button");
            if(maxTimeLine.getStatus()!= Animation.Status.RUNNING){
                maxTimeLine.play();
                play.setText("Pause");
            }else {
                maxTimeLine.pause();
                play.setText("Play");
            }
    }

    @FXML
    public void stop(){
        System.out.println("Enter stop button");
        maxTimeLine.stop();
        play.setText("Play");

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
