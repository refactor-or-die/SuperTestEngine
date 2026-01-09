package org.openjfx.editorBackend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;
import java.util.List;

public class Document {
    private List<String> content;

    private final StringProperty content2electricboogaloo;

    public Document(){
        content2electricboogaloo = new SimpleStringProperty("");
        content = new LinkedList<String>();
        content.add("#Ziemniak#");
    }

    public StringProperty textProperty() {
        return content2electricboogaloo;
    }

    public void setContent(String content) {
        this.content2electricboogaloo.set(content);
    }

    public String getContent2() {
        return content2electricboogaloo.get();
    }

    public List<String> getContent(){
        return content;
    }
    //todo
}
