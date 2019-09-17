module projects {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens proA;

    opens proA.game;
    // For my particular case, I had Forgotten this one
    opens proA.viewAndController;
}
