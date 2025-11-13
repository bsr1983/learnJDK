package com.ibsrapp.jdk9.optional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * JDK9 Optional增强示例
 * 
 * JDK9对Optional类进行了增强，新增了两个有用的方法：
 * 1. ifPresentOrElse() - 如果值存在则执行一个操作，否则执行另一个操作
 * 2. or() - 如果值不存在，则返回另一个Optional
 * 
 * 这些方法使得Optional的使用更加灵活和方便。
 */
public class OptionalEnhancementExample {

    /**
     * 示例1：ifPresentOrElse() - 条件执行
     * 如果值存在则执行一个操作，否则执行另一个操作
     */
    public static void example1_IfPresentOrElse() {
        System.out.println("=== 示例1：ifPresentOrElse()条件执行 ===");
        
        // 值存在的情况
        Optional<String> present = Optional.of("Hello");
        present.ifPresentOrElse(
            value -> System.out.println("值存在：" + value),
            () -> System.out.println("值不存在")
        );
        
        // 值不存在的情况
        Optional<String> empty = Optional.empty();
        empty.ifPresentOrElse(
            value -> System.out.println("值存在：" + value),
            () -> System.out.println("值不存在，执行替代操作")
        );
        
        // 实际应用：处理用户信息
        Optional<String> username = Optional.of("Alice");
        username.ifPresentOrElse(
            name -> System.out.println("欢迎，" + name),
            () -> System.out.println("请先登录")
        );
    }

    /**
     * 示例2：or() - 提供备选Optional
     * 如果当前Optional为空，则返回另一个Optional
     */
    public static void example2_Or() {
        System.out.println("\n=== 示例2：or()提供备选Optional ===");
        
        // 当前Optional有值
        Optional<String> primary = Optional.of("主要值");
        Optional<String> fallback = Optional.of("备选值");
        
        Optional<String> result1 = primary.or(() -> fallback);
        System.out.println("主要值存在时：" + result1.get());
        
        // 当前Optional为空
        Optional<String> empty = Optional.empty();
        Optional<String> result2 = empty.or(() -> fallback);
        System.out.println("主要值不存在时：" + result2.get());
        
        // 链式调用
        Optional<String> result3 = empty
            .or(() -> Optional.of("第一备选"))
            .or(() -> Optional.of("第二备选"));
        System.out.println("链式备选：" + result3.get());
    }

    /**
     * 示例3：实际应用场景 - 配置查找
     * 从多个来源查找配置，使用or()提供备选
     */
    public static Optional<String> getConfigFromEnv() {
        // 模拟从环境变量获取配置
        return Optional.empty(); // 假设环境变量中没有配置
    }
    
    public static Optional<String> getConfigFromFile() {
        // 模拟从文件获取配置
        return Optional.of("配置文件中的值");
    }
    
    public static Optional<String> getConfigFromDatabase() {
        // 模拟从数据库获取配置
        return Optional.of("数据库中的值");
    }
    
    public static void example3_ConfigLookup() {
        System.out.println("\n=== 示例3：配置查找应用场景 ===");
        
        // 按优先级查找配置：环境变量 -> 文件 -> 数据库
        Optional<String> config = getConfigFromEnv()
            .or(() -> getConfigFromFile())
            .or(() -> getConfigFromDatabase());
        
        config.ifPresentOrElse(
            value -> System.out.println("找到配置：" + value),
            () -> System.out.println("未找到任何配置")
        );
    }

    /**
     * 示例4：实际应用场景 - 用户认证
     * 使用ifPresentOrElse()处理认证结果
     */
    public static Optional<String> authenticateUser(String username, String password) {
        // 模拟认证逻辑
        if ("admin".equals(username) && "password".equals(password)) {
            return Optional.of("认证成功");
        }
        return Optional.empty();
    }
    
    public static void example4_UserAuthentication() {
        System.out.println("\n=== 示例4：用户认证应用场景 ===");
        
        // 成功认证
        Optional<String> authResult1 = authenticateUser("admin", "password");
        authResult1.ifPresentOrElse(
            message -> {
                System.out.println(message);
                System.out.println("允许访问系统");
            },
            () -> {
                System.out.println("认证失败");
                System.out.println("拒绝访问");
            }
        );
        
        // 失败认证
        Optional<String> authResult2 = authenticateUser("admin", "wrong");
        authResult2.ifPresentOrElse(
            message -> System.out.println(message),
            () -> System.out.println("认证失败：用户名或密码错误")
        );
    }

    /**
     * 示例5：组合使用ifPresentOrElse()和or()
     */
    public static void example5_CombinedUsage() {
        System.out.println("\n=== 示例5：组合使用 ===");
        
        // 场景：查找用户，如果不存在则创建默认用户
        Optional<String> user1 = Optional.of("Alice");
        Optional<String> user2 = Optional.empty();
        
        // 方式1：使用or()提供备选，然后使用ifPresentOrElse()
        user1.or(() -> Optional.of("默认用户"))
             .ifPresentOrElse(
                 user -> System.out.println("使用用户：" + user),
                 () -> System.out.println("无用户可用")
             );
        
        user2.or(() -> Optional.of("默认用户"))
             .ifPresentOrElse(
                 user -> System.out.println("使用用户：" + user),
                 () -> System.out.println("无用户可用")
             );
    }

    /**
     * 示例6：与JDK8的Optional方法对比
     */
    public static void example6_Comparison() {
        System.out.println("\n=== 示例6：与JDK8方法对比 ===");
        
        Optional<String> optional = Optional.empty();
        
        // JDK8方式：需要两次调用
        if (optional.isPresent()) {
            System.out.println("值：" + optional.get());
        } else {
            System.out.println("无值");
        }
        
        // JDK9方式：一次调用完成
        optional.ifPresentOrElse(
            value -> System.out.println("值：" + value),
            () -> System.out.println("无值")
        );
        
        // JDK8方式：提供备选值
        String result1 = optional.orElse("默认值");
        System.out.println("JDK8方式结果：" + result1);
        
        // JDK9方式：提供备选Optional（更灵活）
        Optional<String> result2 = optional.or(() -> Optional.of("默认值"));
        System.out.println("JDK9方式结果：" + result2.get());
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK9 Optional增强示例 ==========\n");
        
        example1_IfPresentOrElse();
        example2_Or();
        example3_ConfigLookup();
        example4_UserAuthentication();
        example5_CombinedUsage();
        example6_Comparison();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

