package proC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import proC.view.GamePane;
import proC.controller.RootController;
import proC.controller.SceneController;


import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    private boolean isPlayMode = false;

    private GamePane gamePane;


    public Main() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project3");

        initRootLayout();
        // 左边
        showGameView();

        // 右边
        showBeginView();


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

//            loader.setController(new SceneController());
            SceneController sceneController = loader.getController();
            sceneController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGameView(){
        gamePane = new GamePane();
        // Set begin overview into the center of root layout.
        gamePane.getStylesheets().add(Main.class.getResource("/css/bootstrap3.css").toExternalForm());
        rootLayout.setCenter(gamePane);
//        BackgroundImage myBI= new BackgroundImage(new Image(Main.class.getResource("/img/grid.jpg").toExternalForm()),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        gamePane.setBackground(new Background(myBI));
        gamePane.start(primaryStage);

//            loader.setController(new BeginController());
//            GamePane gameController = loader.getController();
//            gameController.setMain(this);
    }

    public GamePane getGamePane() {
        return gamePane;
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
