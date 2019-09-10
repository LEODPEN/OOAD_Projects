package proA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("proA.fxml"));
//        AnchorPane root = (AnchorPane)FXMLLoader.load(Main.class.getResource("proA.fxml"));

        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 500, 522));
        primaryStage.setTitle("");
        Scene scene = new Scene(root,500,444);

        scene.getStylesheets().add(Main.class.getResource("bootstrap3.css").toExternalForm());
        primaryStage.setScene(scene);

//        primaryStage.setScene(new Scene(loader.<ScrollPane>getRoot(), 800, 600));


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
