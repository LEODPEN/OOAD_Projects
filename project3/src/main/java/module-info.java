module projects {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.almasb.fxgl.entity;
    requires com.almasb.fxgl.all;
//    opens proB;

    opens proC.viewController;
    opens proC;
}
