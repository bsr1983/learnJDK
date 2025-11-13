package com.ibsrapp.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * JDK8 Lambda表达式示例
 * 
 * Lambda表达式是JDK8引入的最重要的特性之一，它允许我们将函数作为方法的参数，
 * 或者将代码本身当作数据处理。Lambda表达式使代码更加简洁、易读。
 * 
 * 语法格式：(参数列表) -> {方法体}
 * 
 * 主要特点：
 * 1. 类型推断：编译器可以根据上下文推断参数类型
 * 2. 方法引用：可以引用已有方法
 * 3. 函数式接口：只包含一个抽象方法的接口
 */
public class LambdaExample {

    /**
     * 示例1：使用Lambda表达式替代匿名内部类
     * 传统方式需要使用匿名内部类实现Runnable接口
     */
    public static void example1_AnonymousClass() {
        System.out.println("=== 示例1：Lambda替代匿名内部类 ===");
        
        // 传统方式：使用匿名内部类
        Runnable oldWay = new Runnable() {
            @Override
            public void run() {
                System.out.println("传统方式：使用匿名内部类");
            }
        };
        
        // Lambda方式：简洁明了
        Runnable newWay = () -> System.out.println("Lambda方式：简洁明了");
        
        oldWay.run();
        newWay.run();
    }

    /**
     * 示例2：带参数的Lambda表达式
     * 使用函数式接口Consumer处理列表元素
     */
    public static void example2_WithParameters() {
        System.out.println("\n=== 示例2：带参数的Lambda表达式 ===");
        
        List<String> names = Arrays.asList("张三", "李四", "王五", "赵六");
        
        // 传统方式
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println("姓名：" + name);
            }
        });
        
        // Lambda方式
        names.forEach(name -> System.out.println("姓名：" + name));
        
        // 更简洁的方式：方法引用
        names.forEach(System.out::println);
    }

    /**
     * 示例3：使用Predicate函数式接口
     * Predicate用于判断条件，返回boolean值
     */
    public static void example3_Predicate() {
        System.out.println("\n=== 示例3：Predicate函数式接口 ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 判断是否为偶数
        Predicate<Integer> isEven = n -> n % 2 == 0;
        
        // 判断是否大于5
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        
        System.out.println("偶数：");
        numbers.stream()
               .filter(isEven)
               .forEach(n -> System.out.print(n + " "));
        
        System.out.println("\n大于5的偶数：");
        numbers.stream()
               .filter(isEven.and(isGreaterThan5))
               .forEach(n -> System.out.print(n + " "));
    }

    /**
     * 示例4：使用Function函数式接口
     * Function用于转换数据，接受一个参数，返回一个结果
     */
    public static void example4_Function() {
        System.out.println("\n=== 示例4：Function函数式接口 ===");
        
        List<String> words = Arrays.asList("hello", "world", "java", "lambda");
        
        // 转换为大写
        Function<String, String> toUpperCase = s -> s.toUpperCase();
        
        // 获取字符串长度
        Function<String, Integer> getLength = String::length;
        
        System.out.println("转换为大写：");
        words.stream()
             .map(toUpperCase)
             .forEach(System.out::println);
        
        System.out.println("字符串长度：");
        words.stream()
             .map(getLength)
             .forEach(len -> System.out.println("长度：" + len));
    }

    /**
     * 示例5：使用Supplier函数式接口
     * Supplier用于提供数据，不接受参数，返回一个结果
     */
    public static void example5_Supplier() {
        System.out.println("\n=== 示例5：Supplier函数式接口 ===");
        
        // 提供当前时间戳
        Supplier<Long> timestampSupplier = () -> System.currentTimeMillis();
        
        // 提供随机数
        Supplier<Double> randomSupplier = () -> Math.random();
        
        System.out.println("当前时间戳：" + timestampSupplier.get());
        System.out.println("随机数：" + randomSupplier.get());
    }

    /**
     * 示例6：自定义函数式接口
     * 使用@FunctionalInterface注解标记函数式接口
     */
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    
    public static void example6_CustomFunctionalInterface() {
        System.out.println("\n=== 示例6：自定义函数式接口 ===");
        
        // 加法
        Calculator add = (a, b) -> a + b;
        
        // 乘法
        Calculator multiply = (a, b) -> a * b;
        
        // 最大值
        Calculator max = (a, b) -> a > b ? a : b;
        
        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 * 5 = " + multiply.calculate(10, 5));
        System.out.println("max(10, 5) = " + max.calculate(10, 5));
    }

    /**
     * 示例7：Lambda表达式中的变量作用域
     * Lambda可以访问外部的final或effectively final变量
     */
    public static void example7_VariableScope() {
        System.out.println("\n=== 示例7：Lambda变量作用域 ===");
        
        final String prefix = "用户：";
        int count = 0; // effectively final变量
        
        List<String> users = Arrays.asList("Alice", "Bob", "Charlie");
        
        users.forEach(user -> {
            // 可以访问外部的final变量
            System.out.println(prefix + user);
            // 注意：不能修改外部的非final变量
            // count++; // 编译错误
        });
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 Lambda表达式示例 ==========\n");
        
        example1_AnonymousClass();
        example2_WithParameters();
        example3_Predicate();
        example4_Function();
        example5_Supplier();
        example6_CustomFunctionalInterface();
        example7_VariableScope();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

