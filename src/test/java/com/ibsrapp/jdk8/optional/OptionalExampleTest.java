package com.ibsrapp.jdk8.optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

/**
 * JDK8 Optional单元测试
 */
public class OptionalExampleTest {

    @Test
    public void testCreateOptional() {
        // 测试创建Optional
        Optional<String> present = Optional.of("Hello");
        assertTrue(present.isPresent());
        assertEquals("Hello", present.get());
        
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testOrElse() {
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        assertEquals("Hello", present.orElse("Default"));
        assertEquals("Default", empty.orElse("Default"));
    }

    @Test
    public void testOrElseGet() {
        Optional<String> empty = Optional.empty();
        
        String result = empty.orElseGet(() -> "通过Supplier提供");
        assertEquals("通过Supplier提供", result);
    }

    @Test
    public void testOrElseThrow() {
        Optional<String> empty = Optional.empty();
        
        assertThrows(RuntimeException.class, () -> {
            empty.orElseThrow(() -> new RuntimeException("值不存在"));
        });
    }

    @Test
    public void testMap() {
        Optional<String> optional = Optional.of("hello");
        
        Optional<String> upperCase = optional.map(String::toUpperCase);
        assertTrue(upperCase.isPresent());
        assertEquals("HELLO", upperCase.get());
        
        Optional<String> empty = Optional.empty();
        Optional<String> emptyMap = empty.map(String::toUpperCase);
        assertFalse(emptyMap.isPresent());
    }

    @Test
    public void testFilter() {
        Optional<Integer> optional1 = Optional.of(10);
        Optional<Integer> optional2 = Optional.of(5);
        
        Optional<Integer> filtered1 = optional1.filter(n -> n > 5);
        Optional<Integer> filtered2 = optional2.filter(n -> n > 5);
        
        assertTrue(filtered1.isPresent());
        assertFalse(filtered2.isPresent());
    }

    @Test
    public void testIfPresent() {
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        StringBuilder sb = new StringBuilder();
        present.ifPresent(sb::append);
        assertEquals("Hello", sb.toString());
        
        StringBuilder sb2 = new StringBuilder();
        empty.ifPresent(sb2::append);
        assertEquals("", sb2.toString());
    }

    @Test
    public void testFindUserById() {
        // 测试查找用户的方法
        Optional<String> user1 = OptionalExample.findUserById(5);
        assertTrue(user1.isPresent());
        assertEquals("用户5", user1.get());
        
        Optional<String> user2 = OptionalExample.findUserById(99);
        assertFalse(user2.isPresent());
    }
}

