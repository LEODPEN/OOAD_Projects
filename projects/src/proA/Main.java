package proA;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import proA.game.Ant;
import proA.game.GameBoard;
import proA.game.Options;
import proA.game.PositionInfo;
import proA.viewAndController.AntGameController;
import proA.viewAndController.BeginController;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

//    private ObservableList<Ant> antData  = FXCollections.observableArrayList();

//    private GameBoard gameBoard;
    private ArrayList<PositionInfo>[][] traceList ;



    private int minState;

    private int maxState;


    public Main() {}

    // Returns the data as an observable list of Ant
//    public ObservableList<Ant> getAntData(){
//        return antData;
//    }

    public void setTraceList(ArrayList<PositionInfo>[][] traceList) {
        this.traceList = traceList;
    }

    public void play(Options o){
        GameBoard gameBoard = new GameBoard();
        gameBoard.run(o.getBeginPoint(),o.getSpeed(),o.getLength());
        minState = gameBoard.getMinState();
        maxState = gameBoard.getMaxState();
        traceList = gameBoard.getTrace();

        System.out.println(Arrays.toString(traceList[minState]));
        System.out.println(Arrays.toString(traceList[maxState]));
        // 新开一个fxml来展示


    }

    //    public GameBoard getGameBoard(){
//        return gameBoard;
//    }
//
//    public void setGameBoard(GameBoard gameBoard){
//        this.gameBoard = gameBoard;
//        for (Ant ant : gameBoard.getAnts()){
//            System.out.println(ant.toString());
//        }
//
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project1");

        initRootLayout();

        showBeginView();


//         original  Parent root = FXMLLoader.load(getClass().getResource("views/proA.fxml"));
//
//         //improve to solve the LoadException
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("viewAndController/proA.fxml"));
//        BeginController beginController =loader.getController();
//        beginController.setMain(this);
//
//        Parent root  = loader.load();
//
//        primaryStage.setTitle("Project1");
//        Scene scene = new Scene(root,500,444);
//
//        scene.getStylesheets().add(Main.class.getResource("resources/css/bootstrap3.css").toExternalForm());
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//
//        primaryStage.setScene(new Scene(loader.<ScrollPane>getRoot(), 800, 600));
//        primaryStage.show();

    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewAndController/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(Main.class.getResource("resources/css/bootstrap3.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBeginView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewAndController/Begin.fxml"));
            AnchorPane beginView = (AnchorPane) loader.load();

            // Set begin overview into the center of root layout.
            rootLayout.setCenter(beginView);
//            loader.setController(new BeginController());
            BeginController beginController = loader.getController();
            beginController.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAntGameView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewAndController/AntGame.fxml"));
            AnchorPane antGameView = (AnchorPane) loader.load();

            // Set begin overview into the center of root layout.
            rootLayout.setCenter(antGameView);
//            loader.setController(new BeginController());
            AntGameController antGameController=loader.getController();
            antGameController.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMinState() {
        return minState;
    }

    public int getMaxState() {
        return maxState;
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ArrayList<PositionInfo>[][] getTraceList() {
        return traceList;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
