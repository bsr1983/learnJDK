package com.ibsrapp.jdk8.methodreference;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * JDK8 方法引用单元测试
 */
public class MethodReferenceExampleTest {

    @Test
    public void testStaticMethodReference() {
        List<String> numbers = Arrays.asList("1", "2", "3");
        
        List<Integer> parsed = numbers.stream()
                                      .map(Integer::parseInt)
                                      .toList();
        
        assertEquals(3, parsed.size());
        assertEquals(1, parsed.get(0));
        assertEquals(2, parsed.get(1));
        assertEquals(3, parsed.get(2));
    }

    @Test
    public void testInstanceMethodReference() {
        List<String> words = Arrays.asList("hello", "world", "java");
        
        List<String> upperCase = words.stream()
                                     .map(String::toUpperCase)
                                     .toList();
        
        assertEquals("HELLO", upperCase.get(0));
        assertEquals("WORLD", upperCase.get(1));
        assertEquals("JAVA", upperCase.get(2));
    }

    @Test
    public void testConstructorReference() {
        List<String> names = Arrays.asList("Alice", "Bob");
        
        List<MethodReferenceExample.Person> persons = names.stream()
                                                          .map(MethodReferenceExample.Person::new)
                                                          .toList();
        
        assertEquals(2, persons.size());
        assertEquals("Alice", persons.get(0).getName());
        assertEquals("Bob", persons.get(1).getName());
    }

    @Test
    public void testMethodReferenceVsLambda() {
        Function<String, Integer> parseInt1 = s -> Integer.parseInt(s);
        Function<String, Integer> parseInt2 = Integer::parseInt;
        
        assertEquals(123, parseInt1.apply("123"));
        assertEquals(123, parseInt2.apply("123"));
    }
}

