package com.ibsrapp.jdk25.features;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JDK25 新特性示例
 * 
 * JDK25是最新的Java版本，主要特性包括：
 * 1. 性能优化和JVM改进
 * 2. API增强
 * 3. 工具和库更新
 * 
 * 注意：JDK25是最新版本，主要特性集中在性能优化和API增强上
 * 语法层面的重大新特性相对较少，更多是对现有特性的完善和优化
 */
public class JDK25FeaturesExample {

    /**
     * 示例1：现代Java特性组合使用
     * 展示JDK8-25特性的综合应用
     */
    public static void example1_ModernJavaFeatures() {
        System.out.println("=== 示例1：现代Java特性组合使用 ===");
        
        // 使用Records（JDK16）
        record Person(String name, int age) {}
        
        List<Person> people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 28)
        );
        
        // 使用Stream API（JDK8）+ Switch表达式（JDK14）
        people.stream()
              .forEach(person -> {
                  String category = switch (person.age()) {
                      case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 -> "年轻";
                      case 25, 26, 27, 28, 29 -> "青年";
                      default -> "成年";
                  };
                  System.out.println(person.name() + ": " + category);
              });
    }

    /**
     * 示例2：虚拟线程应用（JDK21）
     * 展示虚拟线程在实际场景中的应用
     */
    public static void example2_VirtualThreads() throws InterruptedException {
        System.out.println("\n=== 示例2：虚拟线程应用（JDK21） ===");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<String> tasks = List.of("任务1", "任务2", "任务3");
            
            for (String task : tasks) {
                executor.submit(() -> {
                    System.out.println("执行：" + task + " (线程：" + 
                        Thread.currentThread().getName() + ")");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            
            Thread.sleep(200);
        }
    }

    /**
     * 示例3：Sequenced Collections（JDK21）
     * 展示有序集合的使用
     */
    public static void example3_SequencedCollections() {
        System.out.println("\n=== 示例3：Sequenced Collections（JDK21） ===");
        
        java.util.SequencedCollection<String> collection = new java.util.ArrayList<>();
        collection.add("first");
        collection.add("second");
        collection.add("third");
        
        System.out.println("第一个：" + collection.getFirst());
        System.out.println("最后一个：" + collection.getLast());
        
        // 反向视图
        java.util.SequencedCollection<String> reversed = collection.reversed();
        System.out.println("反向：" + reversed);
    }

    /**
     * 示例4：Text Blocks + String Templates（JDK15 + JDK22预览）
     * 展示文本块和字符串模板的使用
     */
    public static void example4_TextFeatures() {
        System.out.println("\n=== 示例4：Text Blocks（JDK15） ===");
        
        String name = "Alice";
        int age = 25;
        
        // 使用Text Blocks
        String message = """
            Hello, %s!
            You are %d years old.
            Welcome to JDK25!
            """.formatted(name, age);
        
        System.out.println(message);
        
        // 注意：String Templates是JDK22的预览特性
        // 需要--enable-preview编译
        System.out.println("String Templates语法示例：");
        System.out.println("STR.\"Hello, \\{name}! You are \\{age} years old.\"");
    }

    /**
     * 示例5：综合应用场景
     * 展示多个JDK版本的特性综合使用
     */
    public static void example5_ComprehensiveUsage() {
        System.out.println("\n=== 示例5：综合应用场景 ===");
        
        // Records + Stream + Pattern Matching
        record Product(String name, double price, String category) {}
        
        List<Product> products = List.of(
            new Product("Laptop", 999.99, "Electronics"),
            new Product("Book", 19.99, "Books"),
            new Product("Phone", 699.99, "Electronics")
        );
        
        // 使用Stream API处理
        double totalElectronics = products.stream()
                                          .filter(p -> "Electronics".equals(p.category()))
                                          .mapToDouble(Product::price)
                                          .sum();
        
        System.out.println("电子产品总价：" + totalElectronics);
        
        // 使用Pattern Matching处理
        products.forEach(product -> {
            String description = switch (product.category()) {
                case "Electronics" -> "电子产品：" + product.name();
                case "Books" -> "书籍：" + product.name();
                default -> "其他：" + product.name();
            };
            System.out.println(description);
        });
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK25 新特性示例 ==========\n");
        System.out.println("JDK25是最新的Java版本，主要特性集中在：");
        System.out.println("1. 性能优化和JVM改进");
        System.out.println("2. API增强和完善");
        System.out.println("3. 对现有特性的优化");
        System.out.println("4. 语法层面的重大新特性相对较少\n");
        
        example1_ModernJavaFeatures();
        try {
            example2_VirtualThreads();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        example3_SequencedCollections();
        example4_TextFeatures();
        example5_ComprehensiveUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

