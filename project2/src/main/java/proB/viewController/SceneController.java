package proB.viewController;

import javafx.fxml.Initializable;
import proB.Main;

import java.net.URL;
import java.util.ResourceBundle;

// 这是开始界面
public class SceneController implements Initializable {

    public Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Begin scene initialized!");
    }

}
