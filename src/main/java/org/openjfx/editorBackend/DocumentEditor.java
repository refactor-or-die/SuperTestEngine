package org.openjfx.editorBackend;

import javafx.beans.property.StringProperty;

import java.util.Stack;

public class DocumentEditor {
    private final Document document;

    private final Stack<DocumentMemento> undoStack = new Stack<>();
    private final Stack<DocumentMemento> redoStack = new Stack<>();
    public DocumentEditor(){
        document = new Document();
    }

    public Document getDocument(){
        return document;
    }

    public void execute(Command command) {
        undoStack.push(new DocumentMemento(document.getContent()));
        redoStack.clear();
        command.execute();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            DocumentMemento memento = undoStack.pop();
            redoStack.push(new DocumentMemento(document.getContent()));
            document.setContent(memento.getState());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            DocumentMemento memento = redoStack.pop();
            undoStack.push(new DocumentMemento(document.getContent()));
            document.setContent(memento.getState());
        }
    }

    public StringProperty textProperty() {
        return document.textProperty();
    }
}
