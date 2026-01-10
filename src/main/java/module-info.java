module superTestEngineFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires org.commonmark;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}