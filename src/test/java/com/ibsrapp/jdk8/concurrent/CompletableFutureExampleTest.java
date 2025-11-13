package com.ibsrapp.jdk8.concurrent;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * JDK8 CompletableFuture单元测试
 */
public class CompletableFutureExampleTest {

    @Test
    public void testBasicUsage() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        
        assertEquals("Hello", future.get());
    }

    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
            .thenApply(s -> s + " World");
        
        assertEquals("Hello World", future.get());
    }

    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        
        CompletableFuture<String> combined = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2);
        
        assertEquals("Hello World", combined.get());
    }

    @Test
    public void testExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException("错误");
        }).exceptionally(ex -> "默认值");
        
        assertEquals("默认值", future.get());
    }

    @Test
    public void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "任务1");
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "任务2");
        
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2);
        allTasks.get();
        
        assertTrue(allTasks.isDone());
        assertEquals("任务1", task1.get());
        assertEquals("任务2", task2.get());
    }
}

