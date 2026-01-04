package org.openjfx.editorBackend;

import java.nio.file.Path;
import java.util.List;

public class Backend {
    private SaveStrategy saveStrategy;

    public Backend() {
        this.saveStrategy = new SaveLossless(Path.of("test.txt"));
    }

    public Backend(SaveStrategy saveStrategy) {
        this.saveStrategy = saveStrategy;
    }

    public void setSaveStrategy(SaveStrategy saveStrategy) {
        this.saveStrategy = saveStrategy;
    }

    public void save(List<String> content) {
        this.saveStrategy.save(content);
    }
}
