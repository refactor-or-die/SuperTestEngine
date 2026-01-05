package org.openjfx.editorBackend;

import java.nio.file.Path;
import java.util.List;

public class FileManager {
    private SaveStrategy saveStrategy;
    private ParseStrategy parseStrategy;

    public void setSaveStrategy(SaveStrategy _saveStrategy){
        saveStrategy = _saveStrategy;
    }

    public void setParseStrategy(ParseStrategy _parseStrategy){
        parseStrategy = _parseStrategy;
    }

    public Document read(Path readPath ){
        return parseStrategy.parse(readPath);
    }
    public void save(Document document, Path savePath){
        saveStrategy.save(document, savePath);
    }
}
