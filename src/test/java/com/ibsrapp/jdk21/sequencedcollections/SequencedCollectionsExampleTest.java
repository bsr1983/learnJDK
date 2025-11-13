package com.ibsrapp.jdk21.sequencedcollections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.SequencedCollection;
import java.util.SequencedSet;
import java.util.SequencedMap;

/**
 * JDK21 Sequenced Collections单元测试
 */
public class SequencedCollectionsExampleTest {

    @Test
    public void testSequencedCollection() {
        SequencedCollection<String> collection = new ArrayList<>();
        collection.add("first");
        collection.add("second");
        collection.add("third");
        
        assertEquals("first", collection.getFirst());
        assertEquals("third", collection.getLast());
        
        collection.addFirst("zero");
        assertEquals("zero", collection.getFirst());
        
        collection.removeFirst();
        assertEquals("first", collection.getFirst());
    }

    @Test
    public void testSequencedSet() {
        SequencedSet<String> set = new LinkedHashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("cherry");
        
        assertEquals("apple", set.getFirst());
        assertEquals("cherry", set.getLast());
        
        SequencedSet<String> reversed = set.reversed();
        assertEquals("cherry", reversed.getFirst());
    }

    @Test
    public void testSequencedMap() {
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        assertEquals("one", map.firstEntry().getKey());
        assertEquals("three", map.lastEntry().getKey());
        
        map.putFirst("zero", 0);
        assertEquals("zero", map.firstEntry().getKey());
    }
}

