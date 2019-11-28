package proC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import proC.type.Mode;
import proC.view.GamePane;
import proC.controller.RootController;
import proC.controller.SceneController;


import java.io.*;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    private GamePane gamePane;


    public Main() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project3");
        this.gamePane=new GamePane();

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
        // Set begin overview into the center of root layout.
        this.gamePane.getStylesheets().add(Main.class.getResource("/css/bootstrap3.css").toExternalForm());
        rootLayout.setCenter(this.gamePane);
//        BackgroundImage myBI= new BackgroundImage(new Image(Main.class.getResource("/img/grid.jpg").toExternalForm()),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        gamePane.setBackground(new Background(myBI));

//            loader.setController(new BeginController());
//            GamePane gameController = loader.getController();
//            gameController.setMain(this);
    }

    public GamePane getGamePane() {
        return gamePane;
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    //读取文件中保存的gamePane对象
    public void readGame(File file) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
        GamePane gamePane=(GamePane) objectInputStream.readObject();
        objectInputStream.close();
        //重置界面视图
        gamePane.resetView();
        //默认重置为设计模式（读取在游玩模式下保存的文件时使用）
        gamePane.applyMode(Mode.CONSTRUCT);

        setGamePane(gamePane);
        showGameView();
        showBeginView();

    }

    //保存当前gamePane对象至文件
    public void saveGame(File file) throws IOException,ClassCastException{
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(getGamePane());
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    //新的gamePane
    public void newGame(){
        setGamePane(new GamePane());
        showGameView();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
