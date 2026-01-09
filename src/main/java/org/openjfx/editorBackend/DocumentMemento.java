package org.openjfx.editorBackend;

public class DocumentMemento {
    private String state;

    public DocumentMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
