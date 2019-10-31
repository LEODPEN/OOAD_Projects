module projects {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
//    opens proB;

    opens proC.viewController;
    opens proC;
}
