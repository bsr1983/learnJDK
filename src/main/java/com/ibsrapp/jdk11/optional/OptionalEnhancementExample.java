package com.ibsrapp.jdk11.optional;

import java.util.Optional;

/**
 * JDK11 Optional增强示例
 * 
 * JDK11为Optional添加了isEmpty()方法，用于检查Optional是否为空。
 * 这是isPresent()的反向操作，使代码更加直观。
 * 
 * isEmpty()方法：
 * - 如果Optional为空，返回true
 * - 如果Optional有值，返回false
 * - 与isPresent()相反
 */
public class OptionalEnhancementExample {

    /**
     * 示例1：isEmpty()方法
     * 基本使用
     */
    public static void example1_IsEmpty() {
        System.out.println("=== 示例1：isEmpty()方法 ===");
        
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        System.out.println("present.isEmpty()：" + present.isEmpty());
        System.out.println("present.isPresent()：" + present.isPresent());
        
        System.out.println("empty.isEmpty()：" + empty.isEmpty());
        System.out.println("empty.isPresent()：" + empty.isPresent());
        
        // isEmpty()是isPresent()的反向操作
        System.out.println("isEmpty() == !isPresent()：" + 
            (present.isEmpty() == !present.isPresent()));
    }

    /**
     * 示例2：使用isEmpty()提高可读性
     * 在某些场景下，isEmpty()比!isPresent()更直观
     */
    public static void example2_Readability() {
        System.out.println("\n=== 示例2：使用isEmpty()提高可读性 ===");
        
        Optional<String> username = Optional.empty();
        
        // JDK8方式：使用!isPresent()
        if (!username.isPresent()) {
            System.out.println("用户名为空，使用默认值");
        }
        
        // JDK11方式：使用isEmpty()（更直观）
        if (username.isEmpty()) {
            System.out.println("用户名为空，使用默认值");
        }
        
        // 实际应用
        Optional<String> result = findUser("admin");
        if (result.isEmpty()) {
            System.out.println("未找到用户，创建默认用户");
        } else {
            System.out.println("找到用户：" + result.get());
        }
    }

    /**
     * 示例3：实际应用场景
     * 在业务逻辑中使用isEmpty()
     */
    public static void example3_RealWorldUsage() {
        System.out.println("\n=== 示例3：实际应用场景 ===");
        
        // 场景1：验证数据
        Optional<String> email = Optional.of("test@example.com");
        if (email.isEmpty()) {
            System.out.println("邮箱不能为空");
        } else {
            System.out.println("邮箱有效：" + email.get());
        }
        
        // 场景2：条件处理
        Optional<Integer> count = Optional.empty();
        count.ifPresentOrElse(
            value -> System.out.println("计数：" + value),
            () -> {
                if (count.isEmpty()) {
                    System.out.println("计数为空，初始化为0");
                }
            }
        );
    }

    /**
     * 辅助方法：模拟查找用户
     */
    private static Optional<String> findUser(String username) {
        if ("admin".equals(username)) {
            return Optional.of("管理员");
        }
        return Optional.empty();
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK11 Optional增强示例 ==========\n");
        
        example1_IsEmpty();
        example2_Readability();
        example3_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

