package com.ibsrapp.jdk11.lambda;

import java.util.List;
import java.util.function.Function;

/**
 * JDK11 Lambda表达式增强示例
 * 
 * JDK11允许在Lambda表达式的参数中使用var关键字进行类型推断。
 * 这使得Lambda表达式可以同时使用类型推断和注解。
 * 
 * 特点：
 * 1. 可以在Lambda参数中使用var
 * 2. 所有参数必须都使用var或都不使用var
 * 3. 可以结合注解使用
 */
public class VarInLambdaExample {

    /**
     * 示例1：在Lambda参数中使用var
     * 基本用法
     */
    public static void example1_BasicUsage() {
        System.out.println("=== 示例1：在Lambda参数中使用var ===");
        
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        // JDK10之前：必须明确类型或使用类型推断
        names.forEach((String name) -> System.out.println("姓名：" + name));
        names.forEach(name -> System.out.println("姓名：" + name));
        
        // JDK11：可以在参数中使用var
        names.forEach((var name) -> System.out.println("姓名：" + name));
        
        // 多个参数时，必须都使用var
        Function<String, String> func1 = (var s) -> s.toUpperCase();
        // 注意：BiFunction需要两个参数，Function只能有一个参数
        java.util.function.BiFunction<String, String, String> func2 = (var s1, var s2) -> s1 + s2;
        
        System.out.println("单参数：" + func1.apply("hello"));
        System.out.println("双参数：" + func2.apply("hello", "world"));
    }

    /**
     * 示例2：var与注解结合使用
     * var允许在Lambda参数中使用注解
     */
    public static void example2_WithAnnotations() {
        System.out.println("\n=== 示例2：var与注解结合使用 ===");
        
        // 注意：虽然var允许使用注解，但实际应用中注解在Lambda参数中使用较少
        // 这里展示语法可能性
        
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        // 使用var可以保持一致性
        names.forEach((var name) -> {
            if (name.length() > 3) {
                System.out.println("长名称：" + name);
            }
        });
    }

    /**
     * 示例3：实际应用场景
     * 在复杂Lambda表达式中使用var提高可读性
     */
    public static void example3_RealWorldUsage() {
        System.out.println("\n=== 示例3：实际应用场景 ===");
        
        // 场景1：处理复杂类型
        List<List<String>> nestedList = List.of(
            List.of("a", "b"),
            List.of("c", "d")
        );
        
        nestedList.forEach((var innerList) -> {
            System.out.println("内部列表：" + innerList);
            innerList.forEach((var item) -> System.out.println("  项：" + item));
        });
        
        // 场景2：函数式接口中使用var
        Function<String, Integer> lengthFunc = (var str) -> str.length();
        System.out.println("字符串长度：" + lengthFunc.apply("Hello"));
    }

    /**
     * 示例4：var的限制
     * 展示使用var时的限制
     */
    public static void example4_Limitations() {
        System.out.println("\n=== 示例4：var的限制 ===");
        
        // ✅ 可以：所有参数都使用var
        Function<String, String> func1 = (var s) -> s.toUpperCase();
        
        // ✅ 可以：都不使用var
        Function<String, String> func2 = (s) -> s.toUpperCase();
        
        // ❌ 不可以：部分参数使用var
        // Function<String, String> func3 = (var s1, String s2) -> s1 + s2; // 编译错误
        
        System.out.println("所有参数必须统一使用var或都不使用");
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK11 Lambda表达式增强示例 ==========\n");
        
        example1_BasicUsage();
        example2_WithAnnotations();
        example3_RealWorldUsage();
        example4_Limitations();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

