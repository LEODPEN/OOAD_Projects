package proA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import proA.viewAndController.*;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project1");

        initRootLayout();

        showBeginView();

        // original  Parent root = FXMLLoader.load(getClass().getResource("views/proA.fxml"));

        // improve to solve the LoadException
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("viewAndController/proA.fxml"));
//        BeginController beginController =loader.getController();
//        beginController.setMain(this);

//        Parent root  = loader.load();

//        primaryStage.setTitle("Project1");
//        Scene scene = new Scene(root,500,444);

//        scene.getStylesheets().add(Main.class.getResource("resources/css/bootstrap3.css").toExternalForm());
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);

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
            loader.setLocation(Main.class.getResource("viewAndController/begin.fxml"));
            AnchorPane beginView = (AnchorPane) loader.load();

            // Set begin overview into the center of root layout.
            rootLayout.setCenter(beginView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
