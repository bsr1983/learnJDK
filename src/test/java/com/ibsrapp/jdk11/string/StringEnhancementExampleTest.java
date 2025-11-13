package com.ibsrapp.jdk11.string;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK11 字符串增强单元测试
 */
public class StringEnhancementExampleTest {

    @Test
    public void testStrip() {
        String str = "  hello  ";
        assertEquals("hello", str.strip());
        assertEquals("hello  ", str.stripLeading());
        assertEquals("  hello", str.stripTrailing());
    }

    @Test
    public void testIsBlank() {
        assertTrue("".isBlank());
        assertTrue("   ".isBlank());
        assertTrue("\t\n\r".isBlank());
        assertFalse("hello".isBlank());
    }

    @Test
    public void testLines() {
        String text = "line1\nline2\nline3";
        assertEquals(3, text.lines().count());
    }

    @Test
    public void testRepeat() {
        String str = "ab";
        assertEquals("ababab", str.repeat(3));
        assertEquals("", str.repeat(0));
    }
}

