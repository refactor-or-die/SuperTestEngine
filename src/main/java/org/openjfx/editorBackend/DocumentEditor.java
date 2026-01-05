package org.openjfx.editorBackend;

public class DocumentEditor {
    private Document document;

    public DocumentEditor(){
        document = new Document();
    }

    public Document getDocument(){
        return document;
    }
}
