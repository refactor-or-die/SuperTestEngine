package org.openjfx.editorBackend;

import java.lang.annotation.Documented;
import java.nio.file.Path;

public interface ParseStrategy {
    Document parse(Path filepath);
}
