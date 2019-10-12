module projects {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
//    opens proB;

    opens proB.viewController;
    opens proB;
}
