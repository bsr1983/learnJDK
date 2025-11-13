package com.ibsrapp.jdk9.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * JDK9 Stream API增强示例
 * 
 * JDK9对Stream API进行了增强，新增了几个有用的方法：
 * 1. takeWhile() - 从流开始处取元素，直到条件不满足
 * 2. dropWhile() - 从流开始处丢弃元素，直到条件不满足
 * 3. ofNullable() - 创建包含单个元素或空的流
 * 4. iterate()增强 - 支持带条件的迭代
 * 
 * 这些方法使得Stream API更加强大和灵活。
 */
public class StreamEnhancementExample {

    /**
     * 示例1：takeWhile() - 取元素直到条件不满足
     * 从流的开始处取元素，直到遇到第一个不满足条件的元素
     */
    public static void example1_TakeWhile() {
        System.out.println("=== 示例1：takeWhile()取元素 ===");
        
        List<Integer> numbers = List.of(2, 4, 6, 8, 9, 10, 12);
        
        // 取所有小于10的偶数
        List<Integer> result = numbers.stream()
                                     .takeWhile(n -> n < 10)
                                     .toList();
        
        System.out.println("原列表：" + numbers);
        System.out.println("takeWhile(n -> n < 10)：" + result);
        // 输出：[2, 4, 6, 8] - 遇到9时停止
        
        // 另一个例子：取所有正数
        List<Integer> numbers2 = List.of(1, 2, 3, -1, 4, 5);
        List<Integer> positives = numbers2.stream()
                                         .takeWhile(n -> n > 0)
                                         .toList();
        System.out.println("原列表：" + numbers2);
        System.out.println("takeWhile(n -> n > 0)：" + positives);
        // 输出：[1, 2, 3] - 遇到-1时停止
    }

    /**
     * 示例2：dropWhile() - 丢弃元素直到条件不满足
     * 从流的开始处丢弃元素，直到遇到第一个不满足条件的元素
     */
    public static void example2_DropWhile() {
        System.out.println("\n=== 示例2：dropWhile()丢弃元素 ===");
        
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 丢弃所有小于5的元素
        List<Integer> result = numbers.stream()
                                     .dropWhile(n -> n < 5)
                                     .toList();
        
        System.out.println("原列表：" + numbers);
        System.out.println("dropWhile(n -> n < 5)：" + result);
        // 输出：[5, 6, 7, 8, 9, 10] - 丢弃1,2,3,4，保留5及之后的元素
        
        // 另一个例子：丢弃所有空格
        List<String> words = List.of("", " ", "  ", "hello", "world");
        List<String> nonEmpty = words.stream()
                                     .dropWhile(String::isBlank)
                                     .toList();
        System.out.println("原列表：" + words);
        System.out.println("dropWhile(String::isBlank)：" + nonEmpty);
    }

    /**
     * 示例3：takeWhile()和dropWhile()的组合使用
     */
    public static void example3_TakeWhileAndDropWhile() {
        System.out.println("\n=== 示例3：takeWhile()和dropWhile()组合 ===");
        
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 先丢弃小于3的元素，再取小于8的元素
        List<Integer> result = numbers.stream()
                                     .dropWhile(n -> n < 3)
                                     .takeWhile(n -> n < 8)
                                     .toList();
        
        System.out.println("原列表：" + numbers);
        System.out.println("dropWhile(n < 3).takeWhile(n < 8)：" + result);
        // 输出：[3, 4, 5, 6, 7]
    }

    /**
     * 示例4：ofNullable() - 创建包含单个元素或空的流
     * 如果值为null，返回空流；否则返回包含该元素的流
     */
    public static void example4_OfNullable() {
        System.out.println("\n=== 示例4：ofNullable()创建流 ===");
        
        // 非null值
        String value1 = "Hello";
        List<String> result1 = Stream.ofNullable(value1).toList();
        System.out.println("非null值：" + result1);
        
        // null值
        String value2 = null;
        List<String> result2 = Stream.ofNullable(value2).toList();
        System.out.println("null值：" + result2);
        
        // 实际应用：处理可能为null的列表元素
        List<String> list = List.of("apple", null, "banana", null, "cherry");
        List<String> nonNulls = list.stream()
                                    .flatMap(Stream::ofNullable)
                                    .toList();
        System.out.println("原列表：" + list);
        System.out.println("过滤null后：" + nonNulls);
    }

    /**
     * 示例5：iterate()增强 - 支持带条件的迭代
     * JDK9的iterate()方法支持带条件的迭代，可以替代传统的for循环
     */
    public static void example5_IterateEnhancement() {
        System.out.println("\n=== 示例5：iterate()增强 ===");
        
        // JDK8的iterate()：无限流
        List<Integer> infinite = Stream.iterate(0, n -> n + 2)
                                      .limit(10)
                                      .toList();
        System.out.println("JDK8方式（需要limit）：" + infinite);
        
        // JDK9的iterate()：带条件的迭代
        List<Integer> finite = Stream.iterate(0, n -> n < 10, n -> n + 2)
                                    .toList();
        System.out.println("JDK9方式（带条件）：" + finite);
        // 输出：[0, 2, 4, 6, 8] - 当n >= 10时停止
        
        // 另一个例子：生成斐波那契数列
        List<Integer> fibonacci = Stream.iterate(
            new int[]{0, 1},
            arr -> arr[0] < 100,
            arr -> new int[]{arr[1], arr[0] + arr[1]}
        ).map(arr -> arr[0])
         .toList();
        System.out.println("斐波那契数列（小于100）：" + fibonacci);
    }

    /**
     * 示例6：实际应用场景
     */
    public static void example6_RealWorldUsage() {
        System.out.println("\n=== 示例6：实际应用场景 ===");
        
        // 场景1：处理日志文件，跳过头部注释行
        List<String> logLines = List.of(
            "# 日志开始",
            "# 时间戳：2024-01-01",
            "INFO: 应用启动",
            "INFO: 加载配置",
            "ERROR: 配置错误",
            "INFO: 应用关闭"
        );
        
        List<String> actualLogs = logLines.stream()
                                          .dropWhile(line -> line.startsWith("#"))
                                          .toList();
        System.out.println("实际日志：" + actualLogs);
        
        // 场景2：处理有序数据，取连续满足条件的元素
        List<Integer> temperatures = List.of(20, 22, 24, 26, 28, 25, 23, 21);
        List<Integer> increasing = temperatures.stream()
                                               .takeWhile(temp -> {
                                                   // 简化示例：假设连续递增
                                                   return true; // 实际应该比较相邻元素
                                               })
                                               .toList();
        System.out.println("温度列表：" + temperatures);
        
        // 场景3：安全处理可能为null的Optional值
        Optional<String> optional = Optional.of("value");
        List<String> result = optional.stream()
                                      .flatMap(Stream::ofNullable)
                                      .toList();
        System.out.println("Optional转List：" + result);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK9 Stream API增强示例 ==========\n");
        
        example1_TakeWhile();
        example2_DropWhile();
        example3_TakeWhileAndDropWhile();
        example4_OfNullable();
        example5_IterateEnhancement();
        example6_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

