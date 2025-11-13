package com.ibsrapp.jdk8.lambda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * JDK8 Lambda表达式单元测试
 */
public class LambdaExampleTest {

    @Test
    public void testPredicate() {
        // 测试Predicate函数式接口
        Predicate<Integer> isEven = n -> n % 2 == 0;
        
        assertTrue(isEven.test(2));
        assertTrue(isEven.test(4));
        assertFalse(isEven.test(3));
        assertFalse(isEven.test(5));
    }

    @Test
    public void testPredicateAnd() {
        // 测试Predicate的and方法
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        
        Predicate<Integer> combined = isEven.and(isGreaterThan5);
        
        assertTrue(combined.test(6));
        assertTrue(combined.test(8));
        assertFalse(combined.test(4)); // 偶数但不大于5
        assertFalse(combined.test(7)); // 大于5但不是偶数
    }

    @Test
    public void testCustomFunctionalInterface() {
        // 测试自定义函数式接口
        LambdaExample.Calculator add = (a, b) -> a + b;
        LambdaExample.Calculator multiply = (a, b) -> a * b;
        
        assertEquals(15, add.calculate(10, 5));
        assertEquals(50, multiply.calculate(10, 5));
    }

    @Test
    public void testLambdaWithList() {
        // 测试Lambda表达式处理列表
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        names.forEach(name -> assertNotNull(name));
        
        long count = names.stream()
                         .filter(name -> name.length() > 4)
                         .count();
        
        assertEquals(2, count); // Alice和Charlie长度大于4
    }
}

