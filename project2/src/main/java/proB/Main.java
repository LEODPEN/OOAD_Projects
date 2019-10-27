package proB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import proB.viewController.RootController;
import proB.viewController.SceneController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    public Main() {}

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project2 : BlackJack");

        initRootLayout();

        showBeginView();

//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//
//        Scene scene = new Scene(root);
////        String css = NewFXMain.class.getResource("/css/Styles.css").toExternalForm();
////        scene.getStylesheets().add(css);
//
//        stage.setScene(scene);
//        stage.show();
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
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/Scene.fxml"));
            AnchorPane beginView = loader.load();

            // Set begin overview into the center of root layout.
            rootLayout.setCenter(beginView);

//            loader.setController(new BeginController());
            SceneController sceneController = loader.getController();
            sceneController.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
