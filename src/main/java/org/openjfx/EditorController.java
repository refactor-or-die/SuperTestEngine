package org.openjfx;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.openjfx.editorBackend.CutCommand;
import org.openjfx.editorBackend.PasteCommand;

import java.io.File;
import java.nio.file.Path;

public class EditorController {

    MainApp app = MainApp.getSingleton();

    final FileChooser fileChooser = new FileChooser();

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea editingArea;

    @FXML
    private Label saveModeLabel;

    public void initialize() {
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
        saveModeLabel.textProperty().bind(
                Bindings.format("Save mode: %s", app.getFileManager().saveModeProperty())
        );
    }

    @FXML
    private void onSave() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        File saveFile = fileChooser.showSaveDialog(stage);
        if (saveFile != null) {
            Path savePath = saveFile.toPath();
            app.getFileManager().save(app.getDocumentEditor().getDocument(), savePath);
        }
    }

    @FXML
    private void onLoad() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        File loadFile = fileChooser.showOpenDialog(stage);
        if (loadFile != null) {
            Path loadPath = loadFile.toPath();
            app.getFileManager().load(app.getDocumentEditor().getDocument(), loadPath);
        }
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
