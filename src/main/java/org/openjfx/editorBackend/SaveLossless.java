package org.openjfx.editorBackend;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveLossless implements SaveStrategy{

    @Override
    public void save(Document document, Path filePath) {
        try {
            Files.writeString(filePath, document.getContent(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
