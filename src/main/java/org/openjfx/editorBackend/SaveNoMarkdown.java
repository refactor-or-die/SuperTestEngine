package org.openjfx.editorBackend;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SaveNoMarkdown implements SaveStrategy {
    private Path filePath;

    public SaveNoMarkdown(Path filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public void save(List<String> content) {
        try {
            Files.write(filePath,
                    content.stream()
                           .map(line -> line.replace("#", ""))
                           .collect(Collectors.toList()),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
