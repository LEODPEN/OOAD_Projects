package proC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proC.viewController.GamePane;
import proC.viewController.RootController;
import proC.viewController.SceneController;


import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    private boolean isPlayMode = false;


    public Main() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project3");

        initRootLayout();

        // 右边
        showBeginView();

        // 左边
        showGameView();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/Root.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(Main.class.getResource("/css/bootstrap3.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            RootController rootController = loader.getController();
            rootController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBeginView() {
        try {
            // Load begin(homepage) overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/Scene.fxml"));
            FlowPane beginView = loader.load();

            // Set begin overview into the right of root layout.
            rootLayout.setRight(beginView);

//            loader.setController(new BeginController());
            SceneController sceneController = loader.getController();
            sceneController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGameView(){
        var pane = new GamePane();
        // Set begin overview into the center of root layout.
        rootLayout.setCenter(pane);
        BackgroundImage myBI= new BackgroundImage(new Image(Main.class.getResource("/img/grid.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));
        pane.start(primaryStage);

//            loader.setController(new BeginController());
//            GamePane gameController = loader.getController();
//            gameController.setMain(this);
    }

    public void runGame(){

    }

    public void showRoomView(){

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
