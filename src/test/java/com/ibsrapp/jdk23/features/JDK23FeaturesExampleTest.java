package com.ibsrapp.jdk23.features;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * JDK23 新特性单元测试
 */
public class JDK23FeaturesExampleTest {

    @Test
    public void testStreamEnhancements() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        
        List<Integer> evens = numbers.stream()
                                    .filter(n -> n % 2 == 0)
                                    .toList();
        
        assertEquals(2, evens.size());
        assertTrue(evens.contains(2));
        assertTrue(evens.contains(4));
    }

    @Test
    public void testCollectionsImprovements() {
        java.util.SequencedCollection<String> collection = new java.util.ArrayList<>();
        collection.add("first");
        collection.add("second");
        
        assertEquals("first", collection.getFirst());
        assertEquals("second", collection.getLast());
    }
}

