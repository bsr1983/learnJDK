package com.ibsrapp.jdk21.sequencedcollections;

import java.util.*;

/**
 * JDK21 Sequenced Collections（有序集合）示例
 * 
 * JDK21引入了Sequenced Collections（有序集合），为集合提供了统一的访问首尾元素的方法。
 * 新增了SequencedCollection、SequencedSet和SequencedMap接口。
 * 
 * 主要特性：
 * 1. SequencedCollection - 有序集合接口
 * 2. SequencedSet - 有序集合接口
 * 3. SequencedMap - 有序映射接口
 * 4. 提供统一的first/last访问方法
 * 5. 提供reversed()方法获取反向视图
 * 
 * 使用场景：
 * - 需要访问集合首尾元素的场景
 * - 需要反向遍历集合的场景
 */
public class SequencedCollectionsExample {

    /**
     * 示例1：SequencedCollection
     * 有序集合的基本操作
     */
    public static void example1_SequencedCollection() {
        System.out.println("=== 示例1：SequencedCollection ===");
        
        // LinkedHashSet实现了SequencedSet
        SequencedCollection<String> collection = new LinkedHashSet<>();
        collection.add("first");
        collection.add("second");
        collection.add("third");
        
        System.out.println("集合：" + collection);
        System.out.println("第一个元素：" + collection.getFirst());
        System.out.println("最后一个元素：" + collection.getLast());
        
        // 在开头添加元素
        collection.addFirst("zero");
        System.out.println("开头添加后：" + collection);
        
        // 在结尾添加元素
        collection.addLast("fourth");
        System.out.println("结尾添加后：" + collection);
        
        // 移除首尾元素
        collection.removeFirst();
        collection.removeLast();
        System.out.println("移除首尾后：" + collection);
    }

    /**
     * 示例2：SequencedSet
     * 有序集合（不允许重复）
     */
    public static void example2_SequencedSet() {
        System.out.println("\n=== 示例2：SequencedSet ===");
        
        SequencedSet<String> set = new LinkedHashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("cherry");
        
        System.out.println("集合：" + set);
        System.out.println("第一个：" + set.getFirst());
        System.out.println("最后一个：" + set.getLast());
        
        // 反向视图
        SequencedSet<String> reversed = set.reversed();
        System.out.println("反向视图：" + reversed);
    }

    /**
     * 示例3：SequencedMap
     * 有序映射
     */
    public static void example3_SequencedMap() {
        System.out.println("\n=== 示例3：SequencedMap ===");
        
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println("映射：" + map);
        System.out.println("第一个条目：" + map.firstEntry());
        System.out.println("最后一个条目：" + map.lastEntry());
        
        // 在开头添加
        map.putFirst("zero", 0);
        System.out.println("开头添加后：" + map);
        
        // 在结尾添加
        map.putLast("four", 4);
        System.out.println("结尾添加后：" + map);
        
        // 反向视图
        SequencedMap<String, Integer> reversed = map.reversed();
        System.out.println("反向视图：" + reversed);
    }

    /**
     * 示例4：reversed()方法
     * 获取集合的反向视图
     */
    public static void example4_Reversed() {
        System.out.println("\n=== 示例4：reversed()方法 ===");
        
        SequencedCollection<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        System.out.println("原集合：" + list);
        
        SequencedCollection<String> reversed = list.reversed();
        System.out.println("反向视图：" + reversed);
        
        // 修改反向视图会影响原集合
        reversed.addFirst("D");
        System.out.println("反向视图添加D后，原集合：" + list);
    }

    /**
     * 示例5：实际应用场景
     * 在业务逻辑中使用有序集合
     */
    public static void example5_RealWorldUsage() {
        System.out.println("\n=== 示例5：实际应用场景 ===");
        
        // 场景1：处理任务队列
        SequencedCollection<String> taskQueue = new ArrayDeque<>();
        taskQueue.addLast("任务1");
        taskQueue.addLast("任务2");
        taskQueue.addLast("任务3");
        
        // 处理第一个任务
        String firstTask = taskQueue.removeFirst();
        System.out.println("处理任务：" + firstTask);
        System.out.println("剩余任务：" + taskQueue);
        
        // 场景2：LRU缓存（使用LinkedHashMap）
        SequencedMap<String, String> cache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3; // 最多缓存3个条目
            }
        };
        
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("缓存：" + cache);
        
        cache.get("key1"); // 访问key1，使其成为最新的
        cache.put("key4", "value4"); // 添加新条目，最旧的key2会被移除
        System.out.println("添加key4后：" + cache);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK21 Sequenced Collections示例 ==========\n");
        
        example1_SequencedCollection();
        example2_SequencedSet();
        example3_SequencedMap();
        example4_Reversed();
        example5_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

