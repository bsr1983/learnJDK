package com.ibsrapp.jdk14.patternmatching;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK14 Pattern Matching for instanceof单元测试
 */
public class PatternMatchingInstanceofExampleTest {

    @Test
    public void testPatternMatching() {
        Object obj = "Hello";
        
        if (obj instanceof String str) {
            assertEquals("Hello", str);
            assertEquals(5, str.length());
        } else {
            fail("应该匹配String类型");
        }
    }

    @Test
    public void testWithCondition() {
        Object number = 42;
        
        if (number instanceof Integer i && i > 0) {
            assertEquals(42, i);
            assertTrue(i > 0);
        } else {
            fail("应该匹配Integer且大于0");
        }
    }
}

