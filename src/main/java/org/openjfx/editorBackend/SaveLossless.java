package org.openjfx.editorBackend;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SaveLossless implements SaveStrategy{
    private Path filePath;

    public SaveLossless(Path filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
    public void save(List<String> content) {
        try {
            Files.write(filePath, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
