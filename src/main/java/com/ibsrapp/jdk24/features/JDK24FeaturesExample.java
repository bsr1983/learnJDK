package com.ibsrapp.jdk24.features;

import java.util.List;
import java.util.Map;

/**
 * JDK24 新特性示例
 * 
 * JDK24继续在API增强和性能优化方面进行改进。
 * 主要特性包括：
 * 1. API增强
 * 2. 性能优化
 * 3. 工具改进
 * 
 * 注意：JDK24相对较新，主要特性集中在API增强和性能优化上
 */
public class JDK24FeaturesExample {

    /**
     * 示例1：API增强
     * JDK24对现有API进行了一些增强
     */
    public static void example1_APIImprovements() {
        System.out.println("=== 示例1：API增强 ===");
        
        // 使用现代Java特性
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        // 使用Stream API
        String result = names.stream()
                            .filter(name -> name.length() > 4)
                            .findFirst()
                            .orElse("未找到");
        
        System.out.println("结果：" + result);
        
        // 使用模式匹配（JDK14+）
        Object value = "Hello";
        if (value instanceof String str && str.length() > 3) {
            System.out.println("长字符串：" + str);
        }
    }

    /**
     * 示例2：集合操作增强
     * JDK24对集合操作进行了一些改进
     */
    public static void example2_CollectionOperations() {
        System.out.println("\n=== 示例2：集合操作增强 ===");
        
        // 使用Sequenced Collections
        java.util.SequencedMap<String, Integer> map = new java.util.LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println("映射：" + map);
        System.out.println("第一个条目：" + map.firstEntry());
        System.out.println("最后一个条目：" + map.lastEntry());
        
        // 反向视图
        java.util.SequencedMap<String, Integer> reversed = map.reversed();
        System.out.println("反向映射：" + reversed);
    }

    /**
     * 示例3：实际应用场景
     * 展示JDK24特性的实际应用
     */
    public static void example3_RealWorldUsage() {
        System.out.println("\n=== 示例3：实际应用场景 ===");
        
        // 数据处理
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        Map<String, List<Integer>> grouped = numbers.stream()
                                                   .collect(java.util.stream.Collectors.groupingBy(
                                                       n -> n % 2 == 0 ? "偶数" : "奇数"
                                                   ));
        
        System.out.println("分组结果：" + grouped);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK24 新特性示例 ==========\n");
        System.out.println("注意：JDK24主要特性集中在API增强和性能优化上");
        System.out.println("语法层面的新特性相对较少\n");
        
        example1_APIImprovements();
        example2_CollectionOperations();
        example3_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

