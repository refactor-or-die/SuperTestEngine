package org.openjfx.editorBackend;

import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;

public class PasteCommand implements Command {

    private final TextArea textArea;

    public PasteCommand(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        textArea.paste();
    }
}
