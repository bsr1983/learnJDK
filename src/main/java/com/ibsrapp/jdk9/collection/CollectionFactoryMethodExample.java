package com.ibsrapp.jdk9.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JDK9 集合工厂方法示例
 * 
 * JDK9引入了便捷的集合工厂方法，可以快速创建不可变的集合。
 * 这些方法包括List.of()、Set.of()和Map.of()等。
 * 
 * 特点：
 * 1. 创建的集合是不可变的（immutable）
 * 2. 不允许null元素（会抛出NullPointerException）
 * 3. 不允许重复元素（Set和Map的key）
 * 4. 语法简洁，代码更易读
 * 5. 性能优化：底层实现经过优化
 * 
 * 与JDK8之前的区别：
 * - JDK8之前需要使用Arrays.asList()或手动创建，且集合是可变的
 * - JDK9的工厂方法创建的集合是不可变的，更安全
 */
public class CollectionFactoryMethodExample {

    /**
     * 示例1：List.of() - 创建不可变列表
     */
    public static void example1_ListOf() {
        System.out.println("=== 示例1：List.of()创建不可变列表 ===");
        
        // 创建包含多个元素的列表
        List<String> list1 = List.of("apple", "banana", "cherry");
        System.out.println("列表1：" + list1);
        
        // 创建空列表
        List<String> list2 = List.of();
        System.out.println("空列表：" + list2);
        
        // 创建单个元素的列表
        List<String> list3 = List.of("single");
        System.out.println("单元素列表：" + list3);
        
        // 注意：创建的列表是不可变的
        try {
            list1.add("orange"); // 会抛出UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("无法修改不可变列表：" + e.getClass().getSimpleName());
        }
        
        // 注意：不允许null元素
        try {
            List<String> listWithNull = List.of("apple", null); // 会抛出NullPointerException
        } catch (NullPointerException e) {
            System.out.println("不允许null元素：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例2：Set.of() - 创建不可变集合
     */
    public static void example2_SetOf() {
        System.out.println("\n=== 示例2：Set.of()创建不可变集合 ===");
        
        // 创建包含多个元素的集合
        Set<String> set1 = Set.of("apple", "banana", "cherry");
        System.out.println("集合1：" + set1);
        
        // 创建空集合
        Set<String> set2 = Set.of();
        System.out.println("空集合：" + set2);
        
        // 创建单个元素的集合
        Set<String> set3 = Set.of("single");
        System.out.println("单元素集合：" + set3);
        
        // 注意：不允许重复元素
        try {
            Set<String> duplicateSet = Set.of("apple", "apple"); // 会抛出IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("不允许重复元素：" + e.getClass().getSimpleName());
        }
        
        // 注意：不允许null元素
        try {
            Set<String> setWithNull = Set.of("apple", null);
        } catch (NullPointerException e) {
            System.out.println("不允许null元素：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例3：Map.of() - 创建不可变映射
     */
    public static void example3_MapOf() {
        System.out.println("\n=== 示例3：Map.of()创建不可变映射 ===");
        
        // 创建包含多个键值对的映射（最多10对）
        Map<String, Integer> map1 = Map.of(
            "apple", 1,
            "banana", 2,
            "cherry", 3
        );
        System.out.println("映射1：" + map1);
        
        // 创建空映射
        Map<String, Integer> map2 = Map.of();
        System.out.println("空映射：" + map2);
        
        // 创建单个键值对的映射
        Map<String, Integer> map3 = Map.of("single", 1);
        System.out.println("单键值对映射：" + map3);
        
        // 注意：不允许重复的key
        try {
            Map<String, Integer> duplicateKey = Map.of("apple", 1, "apple", 2);
        } catch (IllegalArgumentException e) {
            System.out.println("不允许重复的key：" + e.getClass().getSimpleName());
        }
        
        // 注意：不允许null key或value
        try {
            Map<String, Integer> mapWithNull = Map.of("apple", null);
        } catch (NullPointerException e) {
            System.out.println("不允许null值：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例4：Map.ofEntries() - 创建包含更多键值对的映射
     * 当需要超过10个键值对时使用
     */
    public static void example4_MapOfEntries() {
        System.out.println("\n=== 示例4：Map.ofEntries()创建大映射 ===");
        
        // 使用Map.ofEntries()可以创建包含更多键值对的映射
        Map<String, Integer> map = Map.ofEntries(
            Map.entry("apple", 1),
            Map.entry("banana", 2),
            Map.entry("cherry", 3),
            Map.entry("date", 4),
            Map.entry("elderberry", 5),
            Map.entry("fig", 6),
            Map.entry("grape", 7),
            Map.entry("honeydew", 8),
            Map.entry("kiwi", 9),
            Map.entry("lemon", 10),
            Map.entry("mango", 11) // 超过10个键值对
        );
        
        System.out.println("大映射大小：" + map.size());
        System.out.println("包含apple：" + map.containsKey("apple"));
        System.out.println("apple的值：" + map.get("apple"));
    }

    /**
     * 示例5：实际应用场景
     * 在代码中使用工厂方法创建配置、常量等
     */
    public static void example5_RealWorldUsage() {
        System.out.println("\n=== 示例5：实际应用场景 ===");
        
        // 场景1：定义常量列表
        List<String> supportedLanguages = List.of("Java", "Python", "JavaScript", "Go");
        System.out.println("支持的语言：" + supportedLanguages);
        
        // 场景2：定义配置映射
        Map<String, String> config = Map.of(
            "database.url", "jdbc:mysql://localhost:3306/mydb",
            "database.username", "admin",
            "database.password", "password123"
        );
        System.out.println("数据库URL：" + config.get("database.url"));
        
        // 场景3：定义允许的状态集合
        Set<String> allowedStatuses = Set.of("ACTIVE", "INACTIVE", "PENDING");
        System.out.println("允许的状态：" + allowedStatuses);
        System.out.println("ACTIVE是否允许：" + allowedStatuses.contains("ACTIVE"));
    }

    /**
     * 示例6：与JDK8之前的方式对比
     */
    public static void example6_Comparison() {
        System.out.println("\n=== 示例6：与JDK8之前的方式对比 ===");
        
        // JDK8之前的方式：使用Arrays.asList()（可变的）
        java.util.List<String> oldWay = new java.util.ArrayList<>();
        oldWay.add("apple");
        oldWay.add("banana");
        oldWay.add("cherry");
        // 或者
        java.util.List<String> oldWay2 = java.util.Arrays.asList("apple", "banana", "cherry");
        System.out.println("JDK8之前方式：" + oldWay2);
        // 注意：Arrays.asList()创建的列表虽然不能改变大小，但可以修改元素
        
        // JDK9的方式：使用List.of()（不可变的）
        List<String> newWay = List.of("apple", "banana", "cherry");
        System.out.println("JDK9方式：" + newWay);
        System.out.println("JDK9方式更简洁，且完全不可变");
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK9 集合工厂方法示例 ==========\n");
        
        example1_ListOf();
        example2_SetOf();
        example3_MapOf();
        example4_MapOfEntries();
        example5_RealWorldUsage();
        example6_Comparison();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

