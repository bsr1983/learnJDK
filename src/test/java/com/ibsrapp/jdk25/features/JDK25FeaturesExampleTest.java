package com.ibsrapp.jdk25.features;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * JDK25 新特性单元测试
 */
public class JDK25FeaturesExampleTest {

    @Test
    public void testModernJavaFeatures() {
        record Person(String name, int age) {}
        
        List<Person> people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 30)
        );
        
        assertEquals(2, people.size());
        assertEquals("Alice", people.get(0).name());
    }

    @Test
    public void testSequencedCollections() {
        java.util.SequencedCollection<String> collection = new java.util.ArrayList<>();
        collection.add("first");
        collection.add("second");
        
        assertEquals("first", collection.getFirst());
        assertEquals("second", collection.getLast());
    }
}

