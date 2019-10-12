package proA.viewAndController;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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

    @FXML
    private Button pause_and_continue;

    @FXML
    private Label runTime;

    @FXML
    private Label maxTimeLabel;

    @FXML
    private Label minTimeLabel;

    private Rectangle stick;

    //stick的x轴位置
    private static final double STICK_X=10d;

    //stick的y轴位置
    private static final double STICK_Y=300d;

    //stick的高度
    private static final double STICK_HEIGHT=10d;

    //ant图片高度
    private static final double ANT_IMAGE_HEIGHT=25d;

    //时间轴动画每帧播放时间，除以速度后，可以表现快慢
    private static final double TIME_LINE_DURATION=1000d;

    private static final Color STICK_FILL=Color.GOLDENROD;

    private static final Image ANT_IMAGE_RIGHT=new Image(Main.class.getResource("resources/images/ant-right.png").toExternalForm());

    private static final Image ANT_IMAGE_LEFT=new Image(Main.class.getResource("resources/images/ant-left.png").toExternalForm());

    private Timeline[] timeLines;

    private ImageView[] antViews;

    private Label[] antLabels;

    private Main main;

    public AntGameController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    /**
     * 根据用户设置的长度创建木杆
     * 木杆宽度为options.length*2+20d
     */
    public void createStick(){
        System.out.println("creating two sticks");

        double length=(double) main.getOptions().getLength()*2+20d;

        stick=new Rectangle( length, STICK_HEIGHT,STICK_FILL);
        stick.setX(STICK_X);
        stick.setY(STICK_Y);
        root.getChildren().add(stick);

        System.out.println("sticks created");
    }

    /**
     * 根据options设置蚂蚁初始位置
     */
    public void createAnts(){

        int[] beginPoint=main.getOptions().getBeginPoint();
        antViews=new ImageView[beginPoint.length];
        antLabels=new Label[beginPoint.length];

        for(int i=0;i<beginPoint.length;i++){

            antViews[i]=new ImageView(ANT_IMAGE_RIGHT);
            antViews[i].setX(STICK_X+2*beginPoint[i]);
            antViews[i].setY(STICK_Y-ANT_IMAGE_HEIGHT);

            antLabels[i]=new Label(String.valueOf(i+1));
            antLabels[i].setLabelFor(antViews[i]);
            antLabels[i].setLayoutX(STICK_X+2*beginPoint[i]+8d);
            antLabels[i].setLayoutY(STICK_Y-ANT_IMAGE_HEIGHT-15d);

            root.getChildren().addAll(antViews[i],antLabels[i]);

        }

        maxTimeLabel.setText("最长时间："+main.getMaxTime()+"s");
        minTimeLabel.setText("最短时间："+main.getMinTime()+"s");

    }

    /**
     * 根据后台计算结果制成时间轴动画并播放，每个蚂蚁的antView（图片）对应一个timeline
     */
    private void playWithState(int state){
        //用每帧运行时间模拟速度
        double duration=TIME_LINE_DURATION/(double)main.getOptions().getSpeed();

        timeLines=new Timeline[antViews.length];

        ArrayList<PositionInfo>[][] traceList=main.getTraceList();


        for(int i=0;i<traceList[state].length;i++){
            timeLines[i]=new Timeline();
            antLabels[i].setVisible(true);
            antViews[i].setVisible(true);

            for (int j = 0; j < traceList[state][i].size() ; j++) {

                double endPosition=STICK_X+(double)traceList[state][i].get(j).getCurrentPosition();

                KeyValue kv=new KeyValue(antViews[i].xProperty(),endPosition);
                KeyFrame kf=new KeyFrame(Duration.millis((j+1)*duration),kv);

                if(traceList[state][i].get(j).getDirection()==-1)
                    antViews[i].setImage(ANT_IMAGE_LEFT);
                else{
                    antViews[i].setImage(ANT_IMAGE_RIGHT);
                }
                //lable动画
                KeyValue kv1=new KeyValue(antLabels[i].layoutXProperty(),STICK_X+(double)traceList[state][i].get(j).getCurrentPosition()+8d);
                KeyFrame kf1=new KeyFrame(Duration.millis((j+1)*duration),kv1);

                //同步显示运行时间
                KeyValue kv2=new KeyValue(runTime.textProperty(),"运行时间："+traceList[state][i].get(j).getTime()/2+"s");
                KeyFrame kf2=new KeyFrame(Duration.millis((j+1)*duration),kv2);

                timeLines[i].getKeyFrames().addAll(kf,kf1,kf2);
                int finalI = i;
                timeLines[i].setOnFinished((actionEvent)->{
                    antLabels[finalI].setVisible(false);
                    antViews[finalI].setVisible(false);
                });
            }
            timeLines[i].play();
        }
        // 蚂蚁下落动画？
        // 国庆放假了！
    }

    @FXML
    public void maxStatePlay(){
        stop();
        playWithState(main.getMaxState());
    }

    @FXML
    public void minStatePlay(){
        stop();
        playWithState(main.getMinState());
    }

    @FXML
    public void randomPlay(){
        stop();
        int traceListLength=main.getTraceList().length;
        Random random=new Random();
        playWithState(random.nextInt(traceListLength));
    }

    @FXML
    public void stop(){
        if(timeLines!=null) {
            for (int i = 0; i < antViews.length; i++) {
                if (timeLines[i] != null) timeLines[i].stop();
            }
        }
        stop.setText("Stop");
    }

    @FXML
    public void pause_and_continue(){
        if(timeLines!=null && pause_and_continue.getText().equals("Pause")){
            for(int i=0;i<antViews.length;i++){
//                if(timeLines[i]!=null)timeLines[i].stop();
                if(timeLines[i]!=null)timeLines[i].pause();
            }
            pause_and_continue.setText("Continue");
        }else if (timeLines!=null && pause_and_continue.getText().equals("Continue")){
            for(int i=0;i<antViews.length;i++){
                if(timeLines[i]!=null)timeLines[i].play();
        }
            pause_and_continue.setText("Pause");
        }
        else {
            pause_and_continue.setText("Pause");
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
