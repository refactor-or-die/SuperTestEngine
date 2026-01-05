package org.openjfx.editorBackend;
import java.util.function.Supplier;

//factory
public enum ESaveStrategies {
    LOSSLESS(SaveLossless::new, "Lossless Save"),
    NO_MARKDOWN(SaveNoMarkdown::new, "No Markdown Save");

    private final Supplier<SaveStrategy> constructor;
    private final String prettyName;

    ESaveStrategies(Supplier<SaveStrategy> constructor, String prettyName) {
        this.constructor = constructor;
        this.prettyName= prettyName;
    }

    public SaveStrategy create() {
        return constructor.get();
    }

    @Override
    public String toString() {
        return prettyName;
    }
}