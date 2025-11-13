package com.ibsrapp.jdk14.patternmatching;

/**
 * JDK14 Pattern Matching for instanceof示例
 * 
 * JDK14引入了Pattern Matching for instanceof，简化了类型检查和转换：
 * 1. 在instanceof检查时可以直接声明变量
 * 2. 变量自动转换为目标类型
 * 3. 减少样板代码，提高可读性
 * 
 * 语法：obj instanceof Type variable
 * - 如果obj是Type类型，variable自动绑定为Type类型
 * - 变量作用域仅限于if语句块内
 */
public class PatternMatchingInstanceofExample {

    /**
     * 示例1：基本用法
     * 使用Pattern Matching简化类型检查
     */
    public static void example1_BasicUsage() {
        System.out.println("=== 示例1：基本用法 ===");
        
        Object obj = "Hello World";
        
        // 传统方式：需要显式转换
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println("字符串长度：" + str.length());
        }
        
        // JDK14方式：自动转换
        if (obj instanceof String str) {
            System.out.println("字符串长度：" + str.length());
            // str已经是String类型，不需要强制转换
        }
    }

    /**
     * 示例2：处理多种类型
     * 使用Pattern Matching处理不同的对象类型
     */
    public static void example2_MultipleTypes() {
        System.out.println("\n=== 示例2：处理多种类型 ===");
        
        Object[] objects = {123, "hello", 45.6, true};
        
        for (Object obj : objects) {
            String description = switch (obj) {
                case Integer i -> "整数：" + i;
                case String s -> "字符串：" + s + "（长度：" + s.length() + "）";
                case Double d -> "浮点数：" + d;
                case Boolean b -> "布尔值：" + b;
                default -> "未知类型：" + obj.getClass().getSimpleName();
            };
            System.out.println(description);
        }
    }

    /**
     * 示例3：结合条件使用
     * Pattern Matching可以与条件结合使用
     */
    public static void example3_WithCondition() {
        System.out.println("\n=== 示例3：结合条件使用 ===");
        
        Object obj = "Hello";
        
        // Pattern Matching + 条件
        if (obj instanceof String str && str.length() > 5) {
            System.out.println("长字符串：" + str);
        } else if (obj instanceof String str) {
            System.out.println("短字符串：" + str);
        }
        
        // 处理数字
        Object number = 42;
        if (number instanceof Integer i && i > 0) {
            System.out.println("正整数：" + i);
        }
    }

    /**
     * 示例4：实际应用场景
     * 在业务逻辑中使用Pattern Matching
     */
    public static void example4_RealWorldUsage() {
        System.out.println("\n=== 示例4：实际应用场景 ===");
        
        // 场景1：处理不同类型的响应
        Object response = "Success";
        if (response instanceof String message) {
            System.out.println("消息响应：" + message);
        } else if (response instanceof Integer code) {
            System.out.println("状态码：" + code);
        }
        
        // 场景2：验证和处理数据
        Object data = 12345;
        if (data instanceof String str && !str.isBlank()) {
            System.out.println("处理字符串数据：" + str);
        } else if (data instanceof Integer num && num > 0) {
            System.out.println("处理数字数据：" + num);
        }
    }

    /**
     * 示例5：与传统方式对比
     * 展示Pattern Matching的优势
     */
    public static void example5_Comparison() {
        System.out.println("\n=== 示例5：与传统方式对比 ===");
        
        Object obj = "Test";
        
        // 传统方式：需要多行代码
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() > 3) {
                System.out.println("长字符串：" + str);
            }
        }
        
        // Pattern Matching：更简洁
        if (obj instanceof String str && str.length() > 3) {
            System.out.println("长字符串：" + str);
        }
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK14 Pattern Matching for instanceof示例 ==========\n");
        
        example1_BasicUsage();
        example2_MultipleTypes();
        example3_WithCondition();
        example4_RealWorldUsage();
        example5_Comparison();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

