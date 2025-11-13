package com.ibsrapp.jdk24.features;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * JDK24 新特性单元测试
 */
public class JDK24FeaturesExampleTest {

    @Test
    public void testAPIImprovements() {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        String result = names.stream()
                            .filter(name -> name.length() > 4)
                            .findFirst()
                            .orElse("未找到");
        
        // Alice长度是5，所以第一个匹配的是Alice
        assertEquals("Alice", result);
    }

    @Test
    public void testCollectionOperations() {
        java.util.SequencedMap<String, Integer> map = new java.util.LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        
        assertEquals("one", map.firstEntry().getKey());
        assertEquals("two", map.lastEntry().getKey());
    }
}

