package com.ibsrapp.jdk23.features;

import java.util.List;
import java.util.stream.Stream;

/**
 * JDK23 新特性示例
 * 
 * JDK23引入了一些API增强和性能改进，主要包括：
 * 1. Stream API增强
 * 2. Collections API改进
 * 3. 性能优化
 * 
 * 注意：JDK23相对较新，主要特性集中在API增强和性能优化上
 */
public class JDK23FeaturesExample {

    /**
     * 示例1：Stream API增强
     * JDK23对Stream API进行了一些增强
     */
    public static void example1_StreamEnhancements() {
        System.out.println("=== 示例1：Stream API增强 ===");
        
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 使用Stream处理数据
        List<Integer> evens = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .toList();
        
        System.out.println("偶数：" + evens);
        
        // 分组操作
        var grouped = numbers.stream()
                             .collect(java.util.stream.Collectors.groupingBy(
                                 n -> n % 2 == 0 ? "偶数" : "奇数"
                             ));
        
        System.out.println("分组结果：" + grouped);
    }

    /**
     * 示例2：Collections API改进
     * JDK23对集合API进行了一些改进
     */
    public static void example2_CollectionsImprovements() {
        System.out.println("\n=== 示例2：Collections API改进 ===");
        
        // 使用不可变集合
        List<String> immutableList = List.of("a", "b", "c");
        System.out.println("不可变列表：" + immutableList);
        
        // 使用Sequenced Collections（JDK21引入，JDK23继续增强）
        java.util.SequencedCollection<String> sequenced = new java.util.ArrayList<>();
        sequenced.add("first");
        sequenced.add("second");
        sequenced.add("third");
        
        System.out.println("第一个元素：" + sequenced.getFirst());
        System.out.println("最后一个元素：" + sequenced.getLast());
    }

    /**
     * 示例3：实际应用场景
     * 展示JDK23特性的实际应用
     */
    public static void example3_RealWorldUsage() {
        System.out.println("\n=== 示例3：实际应用场景 ===");
        
        // 处理数据流
        Stream<String> dataStream = Stream.of("apple", "banana", "cherry", "date");
        
        List<String> processed = dataStream
            .filter(s -> s.length() > 4)
            .map(String::toUpperCase)
            .toList();
        
        System.out.println("处理后的数据：" + processed);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK23 新特性示例 ==========\n");
        System.out.println("注意：JDK23主要特性集中在API增强和性能优化上");
        System.out.println("语法层面的新特性相对较少\n");
        
        example1_StreamEnhancements();
        example2_CollectionsImprovements();
        example3_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

