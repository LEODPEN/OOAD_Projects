module projects3 {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.almasb.fxgl.entity;
    requires com.almasb.fxgl.all;
    requires kotlin.stdlib;
    opens proC.view;
    opens proC.controller;
    opens proC.fxgl.factory;
    opens proC;
    exports proC.fxgl;
}
