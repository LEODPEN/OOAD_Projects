package proA.viewAndController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import proA.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {


    @FXML
    private MenuBar head;

    public Main main;



    public void setMain(Main main) {
        this.main = main;
    }

    public void again(){
        main.showBeginView();
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
