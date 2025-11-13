package com.ibsrapp.jdk10.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JDK10 集合copyOf()方法示例
 * 
 * JDK10为List、Set和Map接口添加了copyOf()静态方法，
 * 用于创建不可变集合的副本。
 * 
 * copyOf()方法的特点：
 * 1. 创建不可变的集合副本
 * 2. 如果输入已经是不可变集合，可能直接返回原集合
 * 3. 不允许null元素
 * 4. 对于Set和Map，不允许重复元素
 * 5. 提供了一种统一的方式来创建不可变集合
 * 
 * 使用场景：
 * - 创建集合的不可变副本
 * - 防止集合被意外修改
 * - 作为方法返回值，保证数据安全
 */
public class CollectionCopyOfExample {

    /**
     * 示例1：List.copyOf() - 创建不可变列表副本
     */
    public static void example1_ListCopyOf() {
        System.out.println("=== 示例1：List.copyOf()创建不可变列表 ===");
        
        // 从可变列表创建不可变副本
        List<String> mutableList = new ArrayList<>();
        mutableList.add("apple");
        mutableList.add("banana");
        mutableList.add("cherry");
        
        List<String> immutableList = List.copyOf(mutableList);
        System.out.println("原列表：" + mutableList);
        System.out.println("不可变副本：" + immutableList);
        
        // 修改原列表不影响不可变副本
        mutableList.add("orange");
        System.out.println("修改后原列表：" + mutableList);
        System.out.println("不可变副本不变：" + immutableList);
        
        // 尝试修改不可变列表会抛出异常
        try {
            immutableList.add("grape");
        } catch (UnsupportedOperationException e) {
            System.out.println("无法修改不可变列表：" + e.getClass().getSimpleName());
        }
        
        // 从不可变列表创建副本（可能直接返回原集合）
        List<String> alreadyImmutable = List.of("x", "y", "z");
        List<String> copy = List.copyOf(alreadyImmutable);
        System.out.println("从不可变列表创建副本：" + copy);
    }

    /**
     * 示例2：Set.copyOf() - 创建不可变集合副本
     */
    public static void example2_SetCopyOf() {
        System.out.println("\n=== 示例2：Set.copyOf()创建不可变集合 ===");
        
        // 从可变集合创建不可变副本
        Set<String> mutableSet = new java.util.HashSet<>();
        mutableSet.add("apple");
        mutableSet.add("banana");
        mutableSet.add("cherry");
        
        Set<String> immutableSet = Set.copyOf(mutableSet);
        System.out.println("原集合：" + mutableSet);
        System.out.println("不可变副本：" + immutableSet);
        
        // 修改原集合不影响不可变副本
        mutableSet.add("orange");
        System.out.println("修改后原集合：" + mutableSet);
        System.out.println("不可变副本不变：" + immutableSet);
        
        // 注意：不允许null元素
        try {
            Set<String> setWithNull = new java.util.HashSet<>();
            setWithNull.add("apple");
            setWithNull.add(null);
            Set.copyOf(setWithNull); // 会抛出NullPointerException
        } catch (NullPointerException e) {
            System.out.println("不允许null元素：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例3：Map.copyOf() - 创建不可变映射副本
     */
    public static void example3_MapCopyOf() {
        System.out.println("\n=== 示例3：Map.copyOf()创建不可变映射 ===");
        
        // 从可变映射创建不可变副本
        Map<String, Integer> mutableMap = new HashMap<>();
        mutableMap.put("apple", 1);
        mutableMap.put("banana", 2);
        mutableMap.put("cherry", 3);
        
        Map<String, Integer> immutableMap = Map.copyOf(mutableMap);
        System.out.println("原映射：" + mutableMap);
        System.out.println("不可变副本：" + immutableMap);
        
        // 修改原映射不影响不可变副本
        mutableMap.put("orange", 4);
        System.out.println("修改后原映射：" + mutableMap);
        System.out.println("不可变副本不变：" + immutableMap);
        
        // 尝试修改不可变映射会抛出异常
        try {
            immutableMap.put("grape", 5);
        } catch (UnsupportedOperationException e) {
            System.out.println("无法修改不可变映射：" + e.getClass().getSimpleName());
        }
        
        // 注意：不允许null key或value
        try {
            Map<String, Integer> mapWithNull = new HashMap<>();
            mapWithNull.put("apple", null);
            Map.copyOf(mapWithNull); // 会抛出NullPointerException
        } catch (NullPointerException e) {
            System.out.println("不允许null值：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例4：实际应用场景 - 作为方法返回值
     * 使用copyOf()创建安全的返回值
     */
    public static List<String> getSupportedLanguages() {
        // 内部使用可变列表
        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        
        // 返回不可变副本，防止外部修改
        return List.copyOf(languages);
    }
    
    public static void example4_AsReturnValue() {
        System.out.println("\n=== 示例4：作为方法返回值 ===");
        
        List<String> languages = getSupportedLanguages();
        System.out.println("支持的语言：" + languages);
        
        // 尝试修改返回值会失败
        try {
            languages.add("Go");
        } catch (UnsupportedOperationException e) {
            System.out.println("无法修改返回值：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例5：实际应用场景 - 保护内部数据
     * 使用copyOf()保护内部集合不被外部修改
     */
    static class Configuration {
        private final Map<String, String> config;
        
        public Configuration(Map<String, String> config) {
            // 创建不可变副本，保护内部数据
            this.config = Map.copyOf(config);
        }
        
        public Map<String, String> getConfig() {
            // 返回不可变副本，防止外部修改
            return Map.copyOf(config);
        }
    }
    
    public static void example5_ProtectInternalData() {
        System.out.println("\n=== 示例5：保护内部数据 ===");
        
        Map<String, String> originalConfig = new HashMap<>();
        originalConfig.put("host", "localhost");
        originalConfig.put("port", "8080");
        
        Configuration config = new Configuration(originalConfig);
        
        // 修改原配置不影响内部配置
        originalConfig.put("host", "changed");
        System.out.println("原配置：" + originalConfig);
        System.out.println("内部配置：" + config.getConfig());
        
        // 尝试修改返回的配置会失败
        Map<String, String> returnedConfig = config.getConfig();
        try {
            returnedConfig.put("host", "hacked");
        } catch (UnsupportedOperationException e) {
            System.out.println("无法修改返回的配置：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例6：与JDK9的工厂方法对比
     */
    public static void example6_Comparison() {
        System.out.println("\n=== 示例6：与JDK9工厂方法对比 ===");
        
        // JDK9方式：直接创建不可变集合
        List<String> list1 = List.of("apple", "banana", "cherry");
        System.out.println("JDK9方式：" + list1);
        
        // JDK10方式：从现有集合创建不可变副本
        List<String> mutable = new ArrayList<>();
        mutable.add("apple");
        mutable.add("banana");
        mutable.add("cherry");
        List<String> list2 = List.copyOf(mutable);
        System.out.println("JDK10方式：" + list2);
        
        System.out.println("JDK9适合：直接创建新的不可变集合");
        System.out.println("JDK10适合：从现有集合创建不可变副本");
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK10 集合copyOf()方法示例 ==========\n");
        
        example1_ListCopyOf();
        example2_SetCopyOf();
        example3_MapCopyOf();
        example4_AsReturnValue();
        example5_ProtectInternalData();
        example6_Comparison();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

