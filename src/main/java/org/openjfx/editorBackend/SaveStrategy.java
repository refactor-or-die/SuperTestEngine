package org.openjfx.editorBackend;

import java.nio.file.Path;

public interface SaveStrategy {
    public void save(Document document, Path filePath);
}
