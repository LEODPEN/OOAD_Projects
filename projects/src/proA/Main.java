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
import proA.viewAndController.RootLayoutController;

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

    private Options options;


    public Main() {}

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    // Returns the data as an observable list of Ant
//    public ObservableList<Ant> getAntData(){
//        return antData;
//    }

    public void setTraceList(ArrayList<PositionInfo>[][] traceList) {
        this.traceList = traceList;
    }

    public void play(Options o){
        this.options =  o;
        GameBoard gameBoard = new GameBoard();
        gameBoard.run(o.getBeginPoint(),o.getSpeed(),o.getLength());
        minState = gameBoard.getMinState();
        maxState = gameBoard.getMaxState();
        traceList = gameBoard.getTrace();

        System.out.println(Arrays.toString(traceList[minState]));
        System.out.println(Arrays.toString(traceList[maxState]));
        // 新开一个fxml来展示

        showAntGameView();
    }


    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project1");

        initRootLayout();

        showBeginView();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewAndController/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(Main.class.getResource("resources/css/bootstrap3.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBeginView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewAndController/Begin.fxml"));
            AnchorPane beginView = loader.load();

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
            AnchorPane antGameView = loader.load();
            // Set begin overview into the center of root layout.

            rootLayout.setCenter(antGameView);

            // 默认播放maxState
            AntGameController antGameController=loader.getController();
            antGameController.setMain(this);
            antGameController.createStick();
            antGameController.createAnts();
            // 先暂停着
            antGameController.stop();
//            antGameController.maxStatePlay();

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
