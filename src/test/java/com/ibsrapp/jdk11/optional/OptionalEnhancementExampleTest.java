package com.ibsrapp.jdk11.optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

/**
 * JDK11 Optional增强单元测试
 */
public class OptionalEnhancementExampleTest {

    @Test
    public void testIsEmpty() {
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        assertFalse(present.isEmpty());
        assertTrue(empty.isEmpty());
        assertEquals(present.isEmpty(), !present.isPresent());
    }
}

