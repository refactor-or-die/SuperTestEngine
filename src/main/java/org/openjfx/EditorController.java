package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;

public class EditorController {

    MainApp app = MainApp.getSingleton();

    @FXML
    private TextArea editingArea;

    public void initialize() {
        editingArea.setText("am I editing anything here?");
        editingArea.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                editingArea.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    KeyCombination saveShortcut = new KeyCodeCombination(
                            KeyCode.S, KeyCombination.CONTROL_DOWN
                    );

                    if (saveShortcut.match(event)) {
                        app.getBackend().save(Arrays.asList(editingArea.getText().split("\n")));
                        event.consume();
                    }
                });
            }
        });

    }



}
