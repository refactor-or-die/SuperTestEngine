package org.openjfx.editorBackend;

import java.nio.file.Path;

public interface SaveStrategy {
    void save(Document document, Path filePath);
}
