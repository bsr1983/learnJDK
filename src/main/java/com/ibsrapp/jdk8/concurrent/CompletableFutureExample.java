package com.ibsrapp.jdk8.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * JDK8 CompletableFuture示例
 * 
 * CompletableFuture是JDK8引入的用于异步编程的强大工具，它实现了Future接口，
 * 并提供了更丰富的API来处理异步操作的结果。
 * 
 * CompletableFuture的特点：
 * 1. 异步执行：可以在后台线程中执行任务
 * 2. 链式调用：支持多个异步操作的组合
 * 3. 异常处理：提供了完善的异常处理机制
 * 4. 组合操作：可以组合多个CompletableFuture
 * 5. 回调函数：支持在任务完成时执行回调
 * 
 * 主要用途：
 * - 异步执行耗时操作
 * - 组合多个异步操作
 * - 处理异步操作的结果和异常
 */
public class CompletableFutureExample {

    /**
     * 示例1：创建和运行CompletableFuture
     * 基本的使用方式
     */
    public static void example1_BasicUsage() throws ExecutionException, InterruptedException {
        System.out.println("=== 示例1：创建和运行CompletableFuture ===");
        
        // 方式1：使用runAsync执行无返回值的任务
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务执行中...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("异步任务完成");
        });
        
        // 等待任务完成
        future1.get();
        System.out.println("主线程继续执行");
        
        // 方式2：使用supplyAsync执行有返回值的任务
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "异步任务返回结果";
        });
        
        String result = future2.get();
        System.out.println("获取结果：" + result);
    }

    /**
     * 示例2：处理结果 - thenApply、thenAccept、thenRun
     * 在任务完成后处理结果
     */
    public static void example2_HandleResult() throws ExecutionException, InterruptedException {
        System.out.println("\n=== 示例2：处理结果 ===");
        
        // thenApply：转换结果
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello")
            .thenApply(s -> s + " World")
            .thenApply(String::toUpperCase);
        
        System.out.println("转换结果：" + future1.get());
        
        // thenAccept：消费结果（无返回值）
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> "Hello")
            .thenAccept(s -> System.out.println("消费结果：" + s));
        
        future2.get();
        
        // thenRun：在任务完成后执行操作（不依赖结果）
        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(() -> "Hello")
            .thenRun(() -> System.out.println("任务完成后的操作"));
        
        future3.get();
    }

    /**
     * 示例3：组合多个CompletableFuture
     * 使用thenCompose和thenCombine组合异步操作
     */
    public static void example3_CombineFutures() throws ExecutionException, InterruptedException {
        System.out.println("\n=== 示例3：组合多个CompletableFuture ===");
        
        // thenCompose：链式组合（前一个的结果作为下一个的输入）
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello")
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        
        System.out.println("链式组合结果：" + future1.get());
        
        // thenCombine：合并两个独立的结果
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");
        
        CompletableFuture<String> combined = future2.thenCombine(future3, (s1, s2) -> s1 + " " + s2);
        System.out.println("合并结果：" + combined.get());
        
        // allOf：等待所有任务完成
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "任务1");
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "任务2");
        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> "任务3");
        
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        allTasks.get();
        System.out.println("所有任务完成");
    }

    /**
     * 示例4：异常处理
     * 使用handle、exceptionally处理异常
     */
    public static void example4_ExceptionHandling() throws ExecutionException, InterruptedException {
        System.out.println("\n=== 示例4：异常处理 ===");
        
        // exceptionally：处理异常，返回默认值
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("发生异常");
            }
            return "正常结果";
        }).exceptionally(ex -> {
            System.out.println("捕获异常：" + ex.getMessage());
            return "异常时的默认值";
        });
        
        System.out.println("异常处理结果：" + future1.get());
        
        // handle：无论成功还是失败都会执行
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "成功结果")
            .handle((result, ex) -> {
                if (ex != null) {
                    return "处理异常：" + ex.getMessage();
                }
                return "处理结果：" + result;
            });
        
        System.out.println("handle结果：" + future2.get());
    }

    /**
     * 示例5：异步回调
     * 使用whenComplete、thenApplyAsync等异步回调
     */
    public static void example5_AsyncCallbacks() throws ExecutionException, InterruptedException {
        System.out.println("\n=== 示例5：异步回调 ===");
        
        // whenComplete：任务完成时执行回调（无论成功还是失败）
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "任务完成";
        }).whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("任务成功：" + result);
            } else {
                System.out.println("任务失败：" + ex.getMessage());
            }
        });
        
        future1.get();
        
        // thenApplyAsync：在另一个线程中执行转换
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello")
            .thenApplyAsync(s -> {
                System.out.println("异步转换线程：" + Thread.currentThread().getName());
                return s + " World";
            });
        
        System.out.println("异步转换结果：" + future2.get());
    }

    /**
     * 示例6：超时处理
     * 使用orTimeout和completeOnTimeout处理超时
     */
    public static void example6_Timeout() {
        System.out.println("\n=== 示例6：超时处理 ===");
        
        // orTimeout：超时后抛出异常
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // 模拟耗时操作
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "完成";
        }).orTimeout(1, TimeUnit.SECONDS);
        
        try {
            future1.get();
        } catch (Exception e) {
            System.out.println("超时异常：" + e.getClass().getSimpleName());
        }
        
        // completeOnTimeout：超时后返回默认值
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "完成";
        }).completeOnTimeout("超时默认值", 1, TimeUnit.SECONDS);
        
        try {
            System.out.println("超时处理结果：" + future2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 示例7：实际应用场景
     * 模拟异步获取数据并处理
     */
    public static CompletableFuture<String> fetchData(String source) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500); // 模拟网络请求
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "数据来自：" + source;
        });
    }
    
    public static void example7_RealWorldUsage() throws ExecutionException, InterruptedException {
        System.out.println("\n=== 示例7：实际应用场景 ===");
        
        // 并行获取多个数据源
        CompletableFuture<String> data1 = fetchData("数据库");
        CompletableFuture<String> data2 = fetchData("缓存");
        CompletableFuture<String> data3 = fetchData("API");
        
        // 等待所有数据获取完成
        CompletableFuture<Void> allData = CompletableFuture.allOf(data1, data2, data3);
        allData.get();
        
        System.out.println(data1.get());
        System.out.println(data2.get());
        System.out.println(data3.get());
        
        // 组合处理：先获取数据，再处理
        CompletableFuture<String> processed = fetchData("数据库")
            .thenApply(data -> "处理后的" + data)
            .thenApply(String::toUpperCase);
        
        System.out.println("处理结果：" + processed.get());
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 CompletableFuture示例 ==========\n");
        
        try {
            example1_BasicUsage();
            example2_HandleResult();
            example3_CombineFutures();
            example4_ExceptionHandling();
            example5_AsyncCallbacks();
            example6_Timeout();
            example7_RealWorldUsage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

