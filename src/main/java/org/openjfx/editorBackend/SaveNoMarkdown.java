package org.openjfx.editorBackend;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class SaveNoMarkdown implements SaveStrategy {

    @Override
    public void save(Document document, Path filePath) {
        try {
            Files.write(filePath,
                    document.getContent().stream()
                           .map(line -> line.replace("#", ""))
                           .collect(Collectors.toList()),
                    StandardCharsets.UTF_8);
            Files.writeString(filePath, document.getContent2().replace("#", ""), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
