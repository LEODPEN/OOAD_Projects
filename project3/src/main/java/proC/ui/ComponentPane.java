package proC.ui;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import proC.BasicGameApp;

import javax.swing.text.html.ImageView;

public class ComponentPane extends FlowPane {

    public ComponentPane(){

        var componetBar= FXGL.getUIFactory().newText("组件栏", Color.BLACK,46.0);

        GridPane componetGrid=new GridPane();
        componetGrid.addColumn(2);
        componetGrid.addRow(5);

        for(int i=0;i<5;i++){
            for(int j=0;j<2;j++){
                CheckBox checkBox=new CheckBox("");
                componetGrid.add(checkBox,j,i);
            }
        }

        GridPane toolGrid=new GridPane();
        toolGrid.addColumn(2);
        toolGrid.addRow(2);

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                CheckBox checkBox=new CheckBox("");
                toolGrid.add(checkBox,j,i);
            }
        }

        GridPane modeGrid=new GridPane();
        modeGrid.addColumn(1);
        modeGrid.addRow(2);
        Button button1=new Button("设计模式");
        Button button2=new Button("游玩模式");
        modeGrid.add(button1,0,0);
        modeGrid.add(button2,0,1);

        var toolBar=FXGL.getUIFactory().newText("工具栏",Color.BLACK,46.0);
        var modeBar=FXGL.getUIFactory().newText("模式栏",Color.BLACK,46.0);

        getChildren().addAll(componetBar,componetGrid,toolBar,toolGrid,modeBar,modeGrid);
    }
}
