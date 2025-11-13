package com.ibsrapp.jdk9.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JDK9 集合工厂方法单元测试
 */
public class CollectionFactoryMethodExampleTest {

    @Test
    public void testListOf() {
        List<String> list = List.of("apple", "banana", "cherry");
        
        assertEquals(3, list.size());
        assertEquals("apple", list.get(0));
        
        // 测试不可变性
        assertThrows(UnsupportedOperationException.class, () -> {
            list.add("orange");
        });
    }

    @Test
    public void testSetOf() {
        Set<String> set = Set.of("apple", "banana", "cherry");
        
        assertEquals(3, set.size());
        assertTrue(set.contains("apple"));
        
        // 测试不允许重复
        assertThrows(IllegalArgumentException.class, () -> {
            Set.of("apple", "apple");
        });
    }

    @Test
    public void testMapOf() {
        Map<String, Integer> map = Map.of("apple", 1, "banana", 2);
        
        assertEquals(2, map.size());
        assertEquals(1, map.get("apple"));
        
        // 测试不允许null
        assertThrows(NullPointerException.class, () -> {
            Map.of("apple", null);
        });
    }

    @Test
    public void testMapOfEntries() {
        Map<String, Integer> map = Map.ofEntries(
            Map.entry("apple", 1),
            Map.entry("banana", 2),
            Map.entry("cherry", 3)
        );
        
        assertEquals(3, map.size());
        assertEquals(1, map.get("apple"));
    }
}

