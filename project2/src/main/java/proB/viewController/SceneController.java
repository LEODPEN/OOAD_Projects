package proB.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import proB.Main;

import java.net.URL;
import java.util.ResourceBundle;

// 这是开始界面
public class SceneController implements Initializable {

    public Main main;

    @FXML
    private Button btnConfirm;

    @FXML
    private Label label;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Begin scene initialized!");
    }

    @FXML
    public void begin(ActionEvent actionEvent) {
        // turn to room fxml and play game.
          main.showRoomView();
    }
}
