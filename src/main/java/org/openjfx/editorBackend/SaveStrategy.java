package org.openjfx.editorBackend;

import java.nio.file.Path;
import java.util.List;

public interface SaveStrategy {
    public void setFilePath(Path filePath);
    public void save(List<String> content);
}
