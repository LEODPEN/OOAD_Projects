package proA.viewAndController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import proA.Main;
import proA.game.Ant;
import proA.game.GameBoard;
import proA.game.Options;

import java.net.URL;
import java.util.ResourceBundle;

// 进行初始数据的获取
public class BeginController implements Initializable{

    @FXML
    public TextField option1;

    @FXML
    public TextField option2;

    @FXML
    public TextField option3;

    @FXML
    public TextField option4;

    @FXML
    public TextField option5;

    @FXML
    public Button btnConfirm;

    @FXML
    public Pane root;

    @FXML
    public Label label;

    @FXML
    public TextArea message;

    // Reference to the main application.
    public Main main;

    public BeginController(){}


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialization!");
        option5.setText("额外功能（尚未开放）");
        option5.setDisable(true);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Options makeOption() {
        Options option = new Options();
        // 考虑未和标准情况
        try {
            option.setNum(Integer.parseInt(option1.getText()));
            if (option.getNum()>10){
                throw new Exception("蚂蚁只数不能超过10！");
            }
            option.setSpeed(Integer.parseInt(option2.getText()));
            String[] pos = option4.getText().split(" ");
            if (pos.length!=option.getNum()){
                throw new Exception("蚂蚁只数与位置数不符合！");
            }
            option.setLength(Integer.parseInt(option3.getText()));
            if (option.getLength()>500){
                throw new Exception("杆长度不能超过500cm！");
            }
            int[] positions = new int[option.getNum()];
            for(int i = 0;i< option.getNum();i++){
                positions[i] = Integer.parseInt(pos[i]);
                if (positions[i]<=0 || positions[i]>=option.getLength()){
                    throw new Exception("蚂蚁位置不能超出杆范围！");
                }
            }
            option.setBeginPoint(positions);
        }catch (Exception e){
            e.printStackTrace();
            // 打印到控制台当作简单日志
            System.out.println("用户传入的参数有些问题.");
            message.setText("ATTENTION：请按照要求进行输入！+\n detail: "+e.getMessage());
        }
        return option;
    }


    @FXML
    public void begin() {
        System.out.println("begin btn has been pushed");
        Options o =  makeOption();
        main.play(o);
    }
}
