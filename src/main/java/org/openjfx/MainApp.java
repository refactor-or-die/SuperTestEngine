package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;


import org.openjfx.editorBackend.*;



public class MainApp extends Application {

    private static MainApp singleton;
    public MainApp() { singleton = this; }
    public static MainApp getSingleton() { return singleton; }

    private final DocumentEditor documentEditor = new DocumentEditor();
    private final FileManager fileManager = new FileManager();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("editor.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        KeyCombination setNoMarkdown = new KeyCodeCombination(
                KeyCode.N,
                KeyCombination.CONTROL_DOWN
        );

        KeyCombination setLossless = new KeyCodeCombination(
                KeyCode.L,
                KeyCombination.CONTROL_DOWN
        );

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (setNoMarkdown.match(event)) {
                fileManager.setSaveStrategy(ESaveStrategies.NO_MARKDOWN);
                event.consume();
            }
            else if (setLossless.match(event)) {
                fileManager.setSaveStrategy(ESaveStrategies.LOSSLESS);
                event.consume();
            }
        });

        stage.setTitle("Super Text Engine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public FileManager getFileManager() {
        return fileManager;
    }
    public DocumentEditor getDocumentEditor() {
        return documentEditor;
    }
}