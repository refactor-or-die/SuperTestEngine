package org.openjfx.editorBackend;

import java.nio.file.Path;

public class Backend {
    private SaveStrategy saveStrategy;

    public Backend() {
        this.saveStrategy = ESaveStrategies.LOSSLESS.create();
    }

    public Backend(SaveStrategy saveStrategy) {
        this.saveStrategy = saveStrategy;
    }

    public void setSaveStrategy(SaveStrategy saveStrategy) {
        this.saveStrategy = saveStrategy;
    }

    public void save(Document document) {
        this.saveStrategy.save(document, Path.of("test.txt"));
    }
}
