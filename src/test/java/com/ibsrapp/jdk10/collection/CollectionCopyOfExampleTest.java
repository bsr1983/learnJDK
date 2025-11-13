package com.ibsrapp.jdk10.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JDK10 集合copyOf()方法单元测试
 */
public class CollectionCopyOfExampleTest {

    @Test
    public void testListCopyOf() {
        List<String> mutable = new ArrayList<>();
        mutable.add("apple");
        mutable.add("banana");
        
        List<String> immutable = List.copyOf(mutable);
        assertEquals(2, immutable.size());
        assertTrue(immutable.contains("apple"));
        
        // 测试不可变性
        assertThrows(UnsupportedOperationException.class, () -> {
            immutable.add("cherry");
        });
        
        // 修改原列表不影响副本
        mutable.add("cherry");
        assertEquals(2, immutable.size());
    }

    @Test
    public void testSetCopyOf() {
        Set<String> mutable = new java.util.HashSet<>();
        mutable.add("apple");
        mutable.add("banana");
        
        Set<String> immutable = Set.copyOf(mutable);
        assertEquals(2, immutable.size());
        assertTrue(immutable.contains("apple"));
        
        // 测试不允许null
        Set<String> setWithNull = new java.util.HashSet<>();
        setWithNull.add("apple");
        setWithNull.add(null);
        assertThrows(NullPointerException.class, () -> {
            Set.copyOf(setWithNull);
        });
    }

    @Test
    public void testMapCopyOf() {
        Map<String, Integer> mutable = new HashMap<>();
        mutable.put("apple", 1);
        mutable.put("banana", 2);
        
        Map<String, Integer> immutable = Map.copyOf(mutable);
        assertEquals(2, immutable.size());
        assertEquals(1, immutable.get("apple"));
        
        // 测试不可变性
        assertThrows(UnsupportedOperationException.class, () -> {
            immutable.put("cherry", 3);
        });
        
        // 测试不允许null值
        Map<String, Integer> mapWithNull = new HashMap<>();
        mapWithNull.put("apple", null);
        assertThrows(NullPointerException.class, () -> {
            Map.copyOf(mapWithNull);
        });
    }
}

