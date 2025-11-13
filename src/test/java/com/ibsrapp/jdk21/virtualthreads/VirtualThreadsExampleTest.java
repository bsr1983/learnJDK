package com.ibsrapp.jdk21.virtualthreads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * JDK21 虚拟线程单元测试
 */
public class VirtualThreadsExampleTest {

    @Test
    public void testCreateVirtualThread() throws InterruptedException {
        Thread virtualThread = Thread.ofVirtual()
            .name("test-virtual-thread")
            .start(() -> {
                assertTrue(Thread.currentThread().isVirtual());
            });
        
        virtualThread.join();
        assertTrue(virtualThread.isVirtual());
    }

    @Test
    public void testVirtualThreadExecutor() throws InterruptedException {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CountDownLatch latch = new CountDownLatch(3);
            
            for (int i = 0; i < 3; i++) {
                executor.submit(() -> {
                    assertTrue(Thread.currentThread().isVirtual());
                    latch.countDown();
                });
            }
            
            assertTrue(latch.await(1, TimeUnit.SECONDS));
        }
    }
}

