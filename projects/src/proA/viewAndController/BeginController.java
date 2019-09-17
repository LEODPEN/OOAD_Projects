package proA.viewAndController;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import proA.Main;

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
        label.setText("yyy?");
    }
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
    }

    @FXML
    public void begin() {
        System.out.println("begin btn has been pushed");
    }
}
