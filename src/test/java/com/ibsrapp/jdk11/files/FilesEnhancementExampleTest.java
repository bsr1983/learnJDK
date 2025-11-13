package com.ibsrapp.jdk11.files;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JDK11 Files增强单元测试
 */
public class FilesEnhancementExampleTest {

    @Test
    public void testReadStringAndWriteString() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        try {
            String content = "Hello World\nTest";
            Files.writeString(tempFile, content);
            
            String readContent = Files.readString(tempFile);
            assertEquals(content, readContent);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}

