package com.ibsrapp.jdk15.textblocks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK15 Text Blocks单元测试
 */
public class TextBlocksExampleTest {

    @Test
    public void testBasicTextBlock() {
        String text = """
            第一行
            第二行
            第三行
            """;
        
        assertTrue(text.contains("第一行"));
        assertTrue(text.contains("第二行"));
        assertTrue(text.contains("第三行"));
    }

    @Test
    public void testFormatted() {
        String name = "Alice";
        int age = 25;
        
        String message = """
            Hello, %s!
            You are %d years old.
            """.formatted(name, age);
        
        assertTrue(message.contains("Alice"));
        assertTrue(message.contains("25"));
    }
}

