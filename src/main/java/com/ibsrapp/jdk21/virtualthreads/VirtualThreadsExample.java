package com.ibsrapp.jdk21.virtualthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * JDK21 虚拟线程（Virtual Threads）示例
 * 
 * JDK21正式引入了虚拟线程（Virtual Threads），这是Java并发编程的重大改进。
 * 虚拟线程是轻量级线程，由JVM管理，可以创建数百万个而不会耗尽系统资源。
 * 
 * 虚拟线程特点：
 * 1. 轻量级：内存占用小（几KB）
 * 2. 高并发：可以创建数百万个
 * 3. 自动调度：由JVM管理，映射到平台线程
 * 4. 阻塞友好：阻塞操作不会阻塞平台线程
 * 5. 简化并发编程
 * 
 * 使用场景：
 * - 高并发I/O操作
 * - 大量短任务
 * - 服务器应用
 */
public class VirtualThreadsExample {

    /**
     * 示例1：创建虚拟线程
     * 使用Thread.ofVirtual()创建虚拟线程
     */
    public static void example1_CreateVirtualThread() throws InterruptedException {
        System.out.println("=== 示例1：创建虚拟线程 ===");
        
        // 方式1：使用Thread.ofVirtual()
        Thread virtualThread = Thread.ofVirtual()
            .name("virtual-thread-1")
            .start(() -> {
                System.out.println("虚拟线程执行：" + Thread.currentThread().getName());
                System.out.println("是否为虚拟线程：" + Thread.currentThread().isVirtual());
            });
        
        virtualThread.join();
        
        // 方式2：使用Thread.startVirtualThread()（便捷方法）
        Thread.startVirtualThread(() -> {
            System.out.println("便捷方式创建虚拟线程：" + Thread.currentThread().getName());
        }).join();
    }

    /**
     * 示例2：虚拟线程执行器
     * 使用Executors.newVirtualThreadPerTaskExecutor()
     */
    public static void example2_VirtualThreadExecutor() throws InterruptedException {
        System.out.println("\n=== 示例2：虚拟线程执行器 ===");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 提交多个任务
            IntStream.range(0, 10).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("任务 " + i + " 在虚拟线程 " + 
                        Thread.currentThread().getName() + " 中执行");
                    try {
                        Thread.sleep(100); // 模拟I/O操作
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });
            
            // 等待所有任务完成
            Thread.sleep(200);
        }
    }

    /**
     * 示例3：高并发场景
     * 展示虚拟线程处理大量并发任务的能力
     */
    public static void example3_HighConcurrency() throws InterruptedException {
        System.out.println("\n=== 示例3：高并发场景 ===");
        
        int taskCount = 1000;
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            long start = System.currentTimeMillis();
            
            // 提交大量任务
            Future<?>[] futures = IntStream.range(0, taskCount)
                .mapToObj(i -> executor.submit(() -> {
                    try {
                        Thread.sleep(10); // 模拟I/O操作
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return i;
                }))
                .toArray(Future[]::new);
            
            // 等待所有任务完成
            for (Future<?> future : futures) {
                future.get();
            }
            
            long duration = System.currentTimeMillis() - start;
            System.out.println("完成 " + taskCount + " 个任务，耗时：" + duration + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 示例4：与传统线程对比
     * 对比虚拟线程和平台线程
     */
    public static void example4_Comparison() {
        System.out.println("\n=== 示例4：与传统线程对比 ===");
        
        // 平台线程
        Thread platformThread = new Thread(() -> {
            System.out.println("平台线程：" + Thread.currentThread().getName());
            System.out.println("是否为虚拟线程：" + Thread.currentThread().isVirtual());
        });
        
        // 虚拟线程
        Thread virtualThread = Thread.ofVirtual()
            .start(() -> {
                System.out.println("虚拟线程：" + Thread.currentThread().getName());
                System.out.println("是否为虚拟线程：" + Thread.currentThread().isVirtual());
            });
        
        try {
            platformThread.start();
            platformThread.join();
            virtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("虚拟线程更适合高并发I/O操作");
    }

    /**
     * 示例5：实际应用场景
     * 模拟HTTP请求处理
     */
    public static void example5_RealWorldUsage() throws InterruptedException {
        System.out.println("\n=== 示例5：实际应用场景 ===");
        
        // 模拟处理HTTP请求
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            String[] requests = {"GET /api/users", "POST /api/orders", "GET /api/products"};
            
            for (String request : requests) {
                executor.submit(() -> {
                    System.out.println("处理请求：" + request + 
                        " (线程：" + Thread.currentThread().getName() + ")");
                    // 模拟I/O操作
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("完成请求：" + request);
                });
            }
            
            Thread.sleep(200);
        }
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK21 虚拟线程示例 ==========\n");
        
        try {
            example1_CreateVirtualThread();
            example2_VirtualThreadExecutor();
            example3_HighConcurrency();
            example4_Comparison();
            example5_RealWorldUsage();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

