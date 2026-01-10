package org.openjfx.editorBackend;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;

public class SaveNoMarkdown implements SaveStrategy {

    @Override
    public void save(Document document, Path filePath) {
        try {
            Parser parser = Parser.builder().build();
            Node node = parser.parse(document.getContent());

            TextContentRenderer renderer =
                    TextContentRenderer.builder().build();

            String plainText = renderer.render(node);

            Files.writeString(filePath, plainText, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Save Failed",e);
        }
    }

    @Override
    public String toString() {
        return "no Markdown";
    }

}
