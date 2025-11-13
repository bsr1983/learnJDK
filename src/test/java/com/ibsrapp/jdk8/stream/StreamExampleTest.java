package com.ibsrapp.jdk8.stream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JDK8 Stream API单元测试
 */
public class StreamExampleTest {

    @Test
    public void testFilter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> evens = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        
        assertEquals(5, evens.size());
        assertTrue(evens.contains(2));
        assertTrue(evens.contains(4));
        assertFalse(evens.contains(1));
    }

    @Test
    public void testMap() {
        List<String> words = Arrays.asList("hello", "world", "java");
        
        List<String> upperCase = words.stream()
                                     .map(String::toUpperCase)
                                     .collect(Collectors.toList());
        
        assertEquals(3, upperCase.size());
        assertEquals("HELLO", upperCase.get(0));
        assertEquals("WORLD", upperCase.get(1));
        assertEquals("JAVA", upperCase.get(2));
    }

    @Test
    public void testFlatMap() {
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d", "e")
        );
        
        List<String> flatList = nestedList.stream()
                                          .flatMap(List::stream)
                                          .collect(Collectors.toList());
        
        assertEquals(5, flatList.size());
        assertTrue(flatList.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    public void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        
        List<Integer> distinct = numbers.stream()
                                       .distinct()
                                       .collect(Collectors.toList());
        
        assertEquals(4, distinct.size());
        assertEquals(Arrays.asList(1, 2, 3, 4), distinct);
    }

    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        int sum = numbers.stream()
                        .reduce(0, Integer::sum);
        
        assertEquals(15, sum);
        
        Optional<Integer> max = numbers.stream()
                                      .reduce(Integer::max);
        
        assertEquals(5, max.orElse(0));
    }

    @Test
    public void testGroupingBy() {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry");
        
        Map<Character, List<String>> grouped = words.stream()
                                                    .collect(Collectors.groupingBy(
                                                        word -> word.charAt(0)
                                                    ));
        
        assertTrue(grouped.containsKey('a'));
        assertTrue(grouped.containsKey('b'));
        assertEquals(2, grouped.get('a').size());
        assertEquals(2, grouped.get('b').size());
    }

    @Test
    public void testAnyMatchAllMatch() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        assertTrue(numbers.stream().anyMatch(n -> n % 2 == 0));
        assertTrue(numbers.stream().allMatch(n -> n > 0));
        assertTrue(numbers.stream().noneMatch(n -> n < 0));
    }
}

