package com.ibsrapp.jdk10.vartypeinference;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDK10 局部变量类型推断单元测试
 */
public class VarTypeInferenceExampleTest {

    @Test
    public void testBasicUsage() {
        var name = "Hello";
        var age = 25;
        
        assertEquals("Hello", name);
        assertEquals(25, age);
        assertEquals(String.class, name.getClass());
        assertEquals(Integer.class, ((Object) age).getClass());
    }

    @Test
    public void testGenericTypes() {
        var map = new HashMap<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        
        assertEquals(2, map.size());
        assertEquals(1, map.get("one"));
    }

    @Test
    public void testInLoops() {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        var count = 0;
        for (var name : names) {
            assertNotNull(name);
            count++;
        }
        
        assertEquals(3, count);
    }

    @Test
    public void testWithStream() {
        var numbers = List.of(1, 2, 3, 4, 5);
        var evens = numbers.stream()
                          .filter(n -> n % 2 == 0)
                          .toList();
        
        assertEquals(2, evens.size());
        assertTrue(evens.contains(2));
        assertTrue(evens.contains(4));
    }
}

