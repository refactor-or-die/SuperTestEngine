package org.openjfx.editorBackend;

import java.nio.file.Path;
import java.util.List;

public class FileManager {
    private SaveStrategy saveStrategy = new SaveLossless();
    private Path saveFilePath;
    private ParseStrategy parseStrategy;

    public void setSaveStrategy(SaveStrategy _saveStrategy){
        saveStrategy = _saveStrategy;
    }

    public void setSaveStrategy (ESaveStrategies saveStrategy) {
        this.setSaveStrategy(saveStrategy.create());
    }

    public void setParseStrategy(ParseStrategy _parseStrategy){
        parseStrategy = _parseStrategy;
    }

    public void setSaveFilePath(Path filePath) {
        saveFilePath = filePath;
    }

    public String getSaveFileName() {
        return saveFilePath.toString();
    }

    public Document read(Path readPath ){
        return parseStrategy.parse(readPath);
    }
    public void save(Document document, Path savePath){
        saveStrategy.save(document, savePath);
    }
}
