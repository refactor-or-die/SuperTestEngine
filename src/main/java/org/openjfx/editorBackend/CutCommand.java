package org.openjfx.editorBackend;

import javafx.scene.control.IndexRange;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class CutCommand implements Command {

    private final IndexRange indexRange;

    public CutCommand(IndexRange indexRange) {
        this.indexRange = indexRange;
    }
    @Override
    public void execute(Document document) {
        String content = document.getContent();
        String transform = content.substring(0, indexRange.getStart()) + content.substring(indexRange.getEnd());
        String cutout = content.substring(indexRange.getStart(), indexRange.getEnd());
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(cutout);
        Clipboard.getSystemClipboard().setContent(clipboardContent);
        document.setContent(transform);
    }
}
