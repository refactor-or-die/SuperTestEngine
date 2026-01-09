package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.openjfx.editorBackend.CutCommand;
import org.openjfx.editorBackend.Document;
import org.openjfx.editorBackend.ESaveStrategies;
import org.openjfx.editorBackend.PasteCommand;

import java.nio.file.Path;
import java.util.Arrays;

public class EditorController {

    MainApp app = MainApp.getSingleton();

    @FXML
    private TextArea editingArea;

    public void initialize() {
        editingArea.setText("am I editing anything here?");

        KeyCombination cutShortcut = new KeyCodeCombination(
                KeyCode.X, KeyCombination.SHORTCUT_DOWN
        );
        KeyCombination pasteShortcut = new KeyCodeCombination(
                KeyCode.V, KeyCombination.SHORTCUT_DOWN
        );
        KeyCombination saveShortcut = new KeyCodeCombination(
                KeyCode.S, KeyCombination.SHORTCUT_DOWN
        );
        KeyCombination redoShortcut = new KeyCodeCombination(
                KeyCode.Y, KeyCombination.SHORTCUT_DOWN
        );
        KeyCombination undoShortcut = new KeyCodeCombination(
                KeyCode.Z, KeyCombination.SHORTCUT_DOWN
        );

        editingArea.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                editingArea.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if (saveShortcut.match(event)) {
                        onSave();
                        event.consume();
                    }
                    else if (undoShortcut.match(event)) {
                        app.getDocumentEditor().undo();
                        event.consume();
                    }
                    else if (redoShortcut.match(event)) {
                        app.getDocumentEditor().redo();
                        event.consume();
                    }
                    else if (pasteShortcut.match(event)) {
                        app.getDocumentEditor().execute(new PasteCommand(editingArea));
                        event.consume();
                    }
                    else if (cutShortcut.match(event)) {
                        app.getDocumentEditor().execute(new CutCommand(editingArea));
                    }
                });
            }
        });

        editingArea.textProperty().bindBidirectional(app.getDocumentEditor().textProperty());
    }

    @FXML
    private void onSave() {
        app.getFileManager().save(app.getDocumentEditor().getDocument(), Path.of("test.txt"));
    }

    @FXML
    private void onLoad() {

    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("This is Super Text Engine\n© 2026 Stanisław Kucharski & Diego Ostoja-Kowalski");
        alert.show();
    }


}
