package org.openjfx.editorBackend;

import javafx.scene.control.IndexRange;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;

public class PasteCommand implements Command {

    private final IndexRange indexRange;

    public PasteCommand(IndexRange indexRange) {
        this.indexRange = indexRange;
    }

    @Override
    public void execute(Document document) {
        String content = document.getContent();
        String newContent = Clipboard.getSystemClipboard().getString();
        String transform = content.substring(0, indexRange.getStart()) + newContent + content.substring(indexRange.getEnd());
        document.setContent(transform);
    }
}
