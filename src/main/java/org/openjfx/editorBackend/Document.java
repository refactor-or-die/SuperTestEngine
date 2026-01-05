package org.openjfx.editorBackend;

import java.util.LinkedList;
import java.util.List;

public class Document {
    private List<String> content;

    public Document(){
        content = new LinkedList<String>();
        content.add("#Ziemniak#");
    }
    public List<String> getContent(){
        return content;
    }
    //todo
}
