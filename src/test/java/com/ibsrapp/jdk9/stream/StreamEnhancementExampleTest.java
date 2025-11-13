package com.ibsrapp.jdk9.stream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * JDK9 Stream API增强单元测试
 */
public class StreamEnhancementExampleTest {

    @Test
    public void testTakeWhile() {
        List<Integer> numbers = List.of(2, 4, 6, 8, 9, 10);
        
        List<Integer> result = numbers.stream()
                                     .takeWhile(n -> n < 10)
                                     .toList();
        
        assertEquals(5, result.size()); // 2, 4, 6, 8, 9都小于10
        assertTrue(result.containsAll(List.of(2, 4, 6, 8, 9)));
    }

    @Test
    public void testDropWhile() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        
        List<Integer> result = numbers.stream()
                                     .dropWhile(n -> n < 4)
                                     .toList();
        
        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of(4, 5, 6)));
    }

    @Test
    public void testOfNullable() {
        List<String> result1 = Stream.<String>ofNullable("hello").toList();
        assertEquals(1, result1.size());
        assertEquals("hello", result1.get(0));
        
        List<String> result2 = Stream.<String>ofNullable(null).toList();
        assertEquals(0, result2.size());
    }

    @Test
    public void testIterateWithCondition() {
        List<Integer> result = Stream.iterate(0, n -> n < 10, n -> n + 2)
                                    .toList();
        
        assertEquals(5, result.size());
        assertEquals(0, result.get(0));
        assertEquals(8, result.get(4));
    }
}

