package proB.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import proB.Main;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private MenuBar head;

    public Main main;

    private void again(){
//        main.showBeginView();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MenuItem mi = head.getMenus().get(0).getItems().get(0);
        mi.setOnAction(actionEvent -> {
            again();
        });
        System.out.println("RootLayout has been initialized.");

    }

}
