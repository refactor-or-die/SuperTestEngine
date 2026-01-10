package org.openjfx.editorBackend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {
    private SaveStrategy saveStrategy = new SaveLossless();
    private final StringProperty saveModeProperty = new SimpleStringProperty(saveStrategy.toString());
    private Path saveFilePath;

    public void setSaveStrategy(SaveStrategy _saveStrategy){
        saveStrategy = _saveStrategy;
        saveModeProperty.set(saveStrategy.toString());
    }

    public void setSaveStrategy (ESaveStrategies saveStrategy) {
        this.setSaveStrategy(saveStrategy.create());
    }


    public void setSaveFilePath(Path filePath) {
        saveFilePath = filePath;
    }

    public String getSaveFileName() {
        return saveFilePath.toString();
    }

    public void save(Document document, Path savePath) {
        saveStrategy.save(document, savePath);
    }

    public void load(Document document, Path loadPath) {
        try {
            String loaded = Files.readString(loadPath, StandardCharsets.UTF_8);
            document.setContent(loaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringProperty saveModeProperty() {
        return saveModeProperty;
    }
}
