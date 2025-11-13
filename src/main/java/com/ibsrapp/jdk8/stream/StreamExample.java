package com.ibsrapp.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JDK8 Stream API示例
 * 
 * Stream API是JDK8引入的用于处理集合数据的强大工具，它提供了一种声明式的方式来处理数据集合。
 * Stream不是数据结构，它更像是一个高级迭代器，可以对数据进行各种操作。
 * 
 * Stream的特点：
 * 1. 不存储数据：Stream不是数据结构，它只是数据源的视图
 * 2. 函数式编程：支持函数式编程风格
 * 3. 惰性求值：中间操作是惰性的，只有终端操作才会触发计算
 * 4. 可消费性：Stream只能被消费一次
 * 5. 并行处理：可以轻松实现并行处理
 * 
 * Stream操作分为两类：
 * - 中间操作：返回Stream，可以链式调用（如filter、map、sorted）
 * - 终端操作：返回结果或副作用（如forEach、collect、reduce）
 */
public class StreamExample {

    /**
     * 示例1：创建Stream
     * 有多种方式可以创建Stream
     */
    public static void example1_CreateStream() {
        System.out.println("=== 示例1：创建Stream ===");
        
        // 方式1：从集合创建
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream1 = list.stream();
        
        // 方式2：使用Stream.of()
        Stream<String> stream2 = Stream.of("x", "y", "z");
        
        // 方式3：使用Arrays.stream()
        Stream<String> stream3 = Arrays.stream(new String[]{"1", "2", "3"});
        
        // 方式4：使用Stream.generate()生成无限流
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);
        
        // 方式5：使用Stream.iterate()生成序列
        Stream<Integer> numbers = Stream.iterate(0, n -> n + 2).limit(10);
        
        System.out.println("从集合创建：");
        stream1.forEach(System.out::println);
        
        System.out.println("随机数（5个）：");
        randomStream.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    /**
     * 示例2：filter - 过滤元素
     * 根据条件过滤Stream中的元素
     */
    public static void example2_Filter() {
        System.out.println("\n=== 示例2：filter过滤操作 ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 过滤出偶数
        List<Integer> evens = numbers.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        
        System.out.println("原列表：" + numbers);
        System.out.println("偶数：" + evens);
        
        // 过滤出大于5的数
        List<Integer> greaterThan5 = numbers.stream()
                                            .filter(n -> n > 5)
                                            .collect(Collectors.toList());
        System.out.println("大于5的数：" + greaterThan5);
    }

    /**
     * 示例3：map - 转换元素
     * 将Stream中的每个元素映射为另一个元素
     */
    public static void example3_Map() {
        System.out.println("\n=== 示例3：map转换操作 ===");
        
        List<String> words = Arrays.asList("hello", "world", "java", "stream");
        
        // 转换为大写
        List<String> upperCase = words.stream()
                                      .map(String::toUpperCase)
                                      .collect(Collectors.toList());
        
        // 获取字符串长度
        List<Integer> lengths = words.stream()
                                    .map(String::length)
                                    .collect(Collectors.toList());
        
        System.out.println("原列表：" + words);
        System.out.println("大写：" + upperCase);
        System.out.println("长度：" + lengths);
    }

    /**
     * 示例4：flatMap - 扁平化映射
     * 将多个Stream合并为一个Stream
     */
    public static void example4_FlatMap() {
        System.out.println("\n=== 示例4：flatMap扁平化操作 ===");
        
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("a", "b", "c"),
            Arrays.asList("d", "e"),
            Arrays.asList("f", "g", "h")
        );
        
        // 将嵌套列表扁平化
        List<String> flatList = nestedList.stream()
                                          .flatMap(List::stream)
                                          .collect(Collectors.toList());
        
        System.out.println("嵌套列表：" + nestedList);
        System.out.println("扁平化后：" + flatList);
    }

    /**
     * 示例5：sorted - 排序
     * 对Stream中的元素进行排序
     */
    public static void example5_Sorted() {
        System.out.println("\n=== 示例5：sorted排序操作 ===");
        
        List<String> names = Arrays.asList("张三", "李四", "王五", "赵六", "钱七");
        
        // 自然排序
        List<String> sorted = names.stream()
                                   .sorted()
                                   .collect(Collectors.toList());
        
        // 自定义排序（按长度）
        List<String> sortedByLength = names.stream()
                                           .sorted(Comparator.comparing(String::length))
                                           .collect(Collectors.toList());
        
        // 倒序
        List<String> reversed = names.stream()
                                     .sorted(Comparator.reverseOrder())
                                     .collect(Collectors.toList());
        
        System.out.println("原列表：" + names);
        System.out.println("自然排序：" + sorted);
        System.out.println("按长度排序：" + sortedByLength);
        System.out.println("倒序：" + reversed);
    }

    /**
     * 示例6：distinct - 去重
     * 去除Stream中的重复元素
     */
    public static void example6_Distinct() {
        System.out.println("\n=== 示例6：distinct去重操作 ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
        
        List<Integer> distinct = numbers.stream()
                                        .distinct()
                                        .collect(Collectors.toList());
        
        System.out.println("原列表：" + numbers);
        System.out.println("去重后：" + distinct);
    }

    /**
     * 示例7：limit和skip - 限制和跳过
     * limit限制元素数量，skip跳过指定数量的元素
     */
    public static void example7_LimitAndSkip() {
        System.out.println("\n=== 示例7：limit和skip操作 ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 取前5个
        List<Integer> first5 = numbers.stream()
                                     .limit(5)
                                     .collect(Collectors.toList());
        
        // 跳过前3个，取后面的
        List<Integer> skip3 = numbers.stream()
                                    .skip(3)
                                    .collect(Collectors.toList());
        
        System.out.println("原列表：" + numbers);
        System.out.println("前5个：" + first5);
        System.out.println("跳过前3个：" + skip3);
    }

    /**
     * 示例8：终端操作 - forEach、collect、reduce
     * 终端操作会触发Stream的计算
     */
    public static void example8_TerminalOperations() {
        System.out.println("\n=== 示例8：终端操作 ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // forEach：遍历每个元素
        System.out.print("遍历：");
        numbers.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // collect：收集结果
        List<Integer> doubled = numbers.stream()
                                       .map(n -> n * 2)
                                       .collect(Collectors.toList());
        System.out.println("每个元素乘以2：" + doubled);
        
        // reduce：归约操作，计算总和
        int sum = numbers.stream()
                        .reduce(0, Integer::sum);
        System.out.println("总和：" + sum);
        
        // reduce：计算最大值
        Optional<Integer> max = numbers.stream()
                                      .reduce(Integer::max);
        System.out.println("最大值：" + max.orElse(0));
        
        // count：计数
        long count = numbers.stream().count();
        System.out.println("元素个数：" + count);
        
        // anyMatch、allMatch、noneMatch：匹配操作
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noNegative = numbers.stream().noneMatch(n -> n < 0);
        
        System.out.println("是否有偶数：" + hasEven);
        System.out.println("是否都为正数：" + allPositive);
        System.out.println("是否没有负数：" + noNegative);
    }

    /**
     * 示例9：分组和分区
     * 使用Collectors.groupingBy和partitioningBy
     */
    public static void example9_GroupingAndPartitioning() {
        System.out.println("\n=== 示例9：分组和分区 ===");
        
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry");
        
        // 按首字母分组
        Map<Character, List<String>> groupedByFirstLetter = words.stream()
                                                                .collect(Collectors.groupingBy(
                                                                    word -> word.charAt(0)
                                                                ));
        
        System.out.println("按首字母分组：" + groupedByFirstLetter);
        
        // 按长度分组
        Map<Integer, List<String>> groupedByLength = words.stream()
                                                          .collect(Collectors.groupingBy(String::length));
        
        System.out.println("按长度分组：" + groupedByLength);
        
        // 分区：按条件分为true和false两组
        Map<Boolean, List<String>> partitioned = words.stream()
                                                      .collect(Collectors.partitioningBy(
                                                          word -> word.startsWith("a")
                                                      ));
        
        System.out.println("以'a'开头分区：" + partitioned);
    }

    /**
     * 示例10：并行流
     * 使用parallelStream实现并行处理
     */
    public static void example10_ParallelStream() {
        System.out.println("\n=== 示例10：并行流 ===");
        
        List<Integer> numbers = IntStream.range(1, 1000000).boxed().collect(Collectors.toList());
        
        // 顺序流
        long start1 = System.currentTimeMillis();
        long sum1 = numbers.stream().mapToLong(Integer::longValue).sum();
        long time1 = System.currentTimeMillis() - start1;
        
        // 并行流
        long start2 = System.currentTimeMillis();
        long sum2 = numbers.parallelStream().mapToLong(Integer::longValue).sum();
        long time2 = System.currentTimeMillis() - start2;
        
        System.out.println("顺序流结果：" + sum1 + "，耗时：" + time1 + "ms");
        System.out.println("并行流结果：" + sum2 + "，耗时：" + time2 + "ms");
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 Stream API示例 ==========\n");
        
        example1_CreateStream();
        example2_Filter();
        example3_Map();
        example4_FlatMap();
        example5_Sorted();
        example6_Distinct();
        example7_LimitAndSkip();
        example8_TerminalOperations();
        example9_GroupingAndPartitioning();
        example10_ParallelStream();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

