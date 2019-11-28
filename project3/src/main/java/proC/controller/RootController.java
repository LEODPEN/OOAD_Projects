package proC.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import proC.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private MenuBar head;

    public Main main;

    private void again(){
        // reset all / clear all

    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        Menu menu = head.getMenus().get(0);

        MenuItem newGame=menu.getItems().get(0);        //新建游戏
        MenuItem saveGame=menu.getItems().get(1);       //保存游戏
        MenuItem readGame=menu.getItems().get(2);       //读取游戏

        newGame.setOnAction(actionEvent->{
            main.newGame();
        });

        saveGame.setOnAction(actionEvent -> {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("保存游戏");
            File file = fileChooser.showSaveDialog(main.getPrimaryStage());

            try {
                main.saveGame(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        readGame.setOnAction(actionEvent -> {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("读取游戏");
            File file=fileChooser.showOpenDialog(main.getPrimaryStage());

            try {
                main.readGame(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

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
