package com.ibsrapp.jdk9.optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

/**
 * JDK9 Optional增强单元测试
 */
public class OptionalEnhancementExampleTest {

    @Test
    public void testIfPresentOrElse() {
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        StringBuilder sb1 = new StringBuilder();
        present.ifPresentOrElse(
            value -> sb1.append(value),
            () -> sb1.append("Empty")
        );
        assertEquals("Hello", sb1.toString());
        
        StringBuilder sb2 = new StringBuilder();
        empty.ifPresentOrElse(
            value -> sb2.append(value),
            () -> sb2.append("Empty")
        );
        assertEquals("Empty", sb2.toString());
    }

    @Test
    public void testOr() {
        Optional<String> primary = Optional.of("Primary");
        Optional<String> fallback = Optional.of("Fallback");
        Optional<String> empty = Optional.empty();
        
        Optional<String> result1 = primary.or(() -> fallback);
        assertEquals("Primary", result1.get());
        
        Optional<String> result2 = empty.or(() -> fallback);
        assertEquals("Fallback", result2.get());
    }
}

