package proC.viewController;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import proC.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private MenuBar head;

    public Main main;

    private void again(){
//        main.showBeginView();
        main.showBeginView();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MenuItem m1 = head.getMenus().get(0).getItems().get(0);
        m1.setOnAction(actionEvent -> {
            again();
        });
//        Menu m = (Menu) head.getMenus().get(0).getItems().get(1);
//        MenuItem m2 = m.getItems().get(0);
//        MenuItem m3 = m.getItems().get(1);
//        m2.setOnAction(actionEvent -> {
//            main.changeLevel(1);
//        });
//        m3.setOnAction(actionEvent -> {
//            main.changeLevel(2);
//        });
        System.out.println("RootLayout has been initialized.");

    }

}
