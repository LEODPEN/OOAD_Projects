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
import proA.game.Options;
import proA.game.PositionInfo;

import java.io.File;
import java.net.URISyntaxException;
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

    private Rectangle stick1;

    private Rectangle stick2;

    //stick的x轴位置
    private static final double STICK_X=20d;

    //stick的y轴位置
    private static final double STICK_Y=300d;

    //stick的高度
    private static final double STICK_HEIGHT=10d;

    //ant图片高度
    private static final double ANT_IMAGE_HEIGHT=25d;

    //时间轴动画每帧播放时间，除以速度后，可以表现快慢
    private static final double TIME_LINE_DURATION=1000d;

    private static final Color STICK_FILL=Color.GOLDENROD;

    private Timeline[] minTimeLines;

    private Timeline[] maxTimeLines;

    private ImageView[] antViews;

    private Main main;

    public AntGameController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    /**
     * 根据用户设置的长度创建木杆
     * 木杆宽度为options.length*2，显示效果更好
     */
    public void createStick(){
        System.out.println("creating two sticks");

        double length=(double) main.getOptions().getLength()*2;

        stick1=new Rectangle( length, STICK_HEIGHT,STICK_FILL);
        stick1.setX(STICK_X);
        stick1.setY(STICK_Y);
        root.getChildren().add(stick1);

//        stick2=new Rectangle(length,STICK_HEIGHT,STICK_FILL);
////        stick2.setX(STICK_X);
////        stick2.setY(STICK_Y/2);
////        root.getChildren().add(stick2);

        System.out.println("sticks created");
    }

    /**
     * 根据options设置蚂蚁初始位置
     */
    public void createAnts(){

        int[] beginPoint=main.getOptions().getBeginPoint();
        antViews=new ImageView[beginPoint.length];

        for(int i=0;i<beginPoint.length;i++){
//            antViews[i]=new ImageView(getClass().getResource("../resources/images/ant.jpeg").toExternalForm());
            antViews[i]=new ImageView("file:///C:\\Users\\74467\\Desktop\\work\\OOAD_Projects\\projects\\src\\proA\\resources\\images\\ant.png");
            antViews[i].setX(STICK_X+beginPoint[i]);
            antViews[i].setY(STICK_Y-ANT_IMAGE_HEIGHT);
            root.getChildren().add(antViews[i]);
        }


    }

    /**
     * 根据后台计算结果制成时间轴动画并播放，每个蚂蚁的antView（图片）对应一个timeline
     */
    public void initTransition(){

        //用每帧运行时间模拟速度
        double duration=TIME_LINE_DURATION/(double)main.getOptions().getSpeed();

        maxTimeLines=new Timeline[antViews.length];

        minTimeLines=new Timeline[antViews.length];


        ArrayList<PositionInfo>[][] traceList=main.getTraceList();
        int minState=main.getMinState();



        for(int i=0;i<traceList[minState].length;i++){
            minTimeLines[i]=new Timeline();
            for (int j = 0; j <traceList[minState][i].size() ; j++) {
                KeyValue kv=new KeyValue(antViews[i].xProperty(),STICK_X+(double)traceList[minState][i].get(j).getCurrentPosition());
                KeyFrame kf=new KeyFrame(Duration.millis((j+1)*duration),kv);
                minTimeLines[i].getKeyFrames().add(kf);
            }
            minTimeLines[i].play();
        }

    }

    @FXML
    public void play(){

        for(int i=0;i<antViews.length;i++){
            if(minTimeLines[i].getStatus()!=Animation.Status.RUNNING){
                play.setText("Play");
                minTimeLines[i].play();
            }
            else{
                play.setText("Pause");
                minTimeLines[i].pause();
            }
        }
    }

    @FXML
    public void stop(){
        for(int i=0;i<antViews.length;i++){
            minTimeLines[i].stop();
        }
        stop.setText("Stop");
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
