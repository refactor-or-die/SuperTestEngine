package org.openjfx.editorBackend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {

    private final StringProperty content;

    public Document(){
        content = new SimpleStringProperty("");
    }

    public StringProperty textProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getContent() {
        return content.get();
    }
}
