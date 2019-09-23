package proA.viewAndController;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import proA.Main;
import proA.game.Ant;
import proA.game.GameBoard;
import proA.game.Options;

import java.net.URL;
import java.util.ArrayList;
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

    // Reference to the main application.
    public Main main;

    public BeginController(){}


    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        btnBegin.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                System.out.println("this is a test.");
//            }
//        });
        System.out.println("initianization!");
        option5.setText("额外功能（尚未开放）");
        option5.setDisable(true);
    }
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
    }

    public Options makeOption() {
        Options option = new Options();
        // 考虑未和标准情况
        try {
            option.setNum(Integer.parseInt(option1.getText()));
            option.setSpeed(Integer.parseInt(option2.getText()));
//        option.setDirection(option3.getText());
            String[] pos = option4.getText().split(" ");
            int[] positions = new int[option.getNum()];
            // option.getNum() = pos.length
            for(int i = 0;i< option.getNum();i++){
                positions[i] = Integer.parseInt(pos[i]);
            }
            option.setBeginPoint(positions);
            option.setLength(Integer.parseInt(option3.getText()));
        }catch (Exception e){
            e.printStackTrace();
            // 打印到控制台当作简单日志
            System.out.println("用户传入的参数有些问题.");
        }

//        System.out.println(option.toString());
        return option;
    }

//    public ArrayList<Ant> makeAnts(Options option){
//        int speed = Integer.parseInt(option.getSpeed());
//        int num = Integer.parseInt(option.getNum());
////        String[] directions = option.getDirection().split(" ");
//        String[] positions = option.getBeginPoint().split(" ");
//        ArrayList<Ant> ants = new ArrayList<Ant>();
//        for (int i = 0; i < num;i++){
//            ants.add(new Ant(Integer.parseInt(positions[i]),Integer.parseInt(directions[i]),speed));
//        }
//        return ants;
//    }

//    public GameBoard makeBoard(ArrayList<Ant> ants){
//        return new GameBoard(ants);
//    }

    @FXML
    public void begin() {
        System.out.println("begin btn has been pushed");
        Options o =  makeOption();

        main.play(o);

//         main.setGameBoard(makeBoard(makeAnts(o)));
        // 打开新的fxml,通过main的函数实现
    }
}
