module pongscreensaver.pongscreensaver {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.testng;
    requires junit;
    requires jdk.compiler;

    opens pongscreensaver.pongscreensaver to javafx.fxml;
    exports pongscreensaver.pongscreensaver;
}