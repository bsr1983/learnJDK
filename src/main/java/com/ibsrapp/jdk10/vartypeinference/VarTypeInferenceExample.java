package com.ibsrapp.jdk10.vartypeinference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDK10 局部变量类型推断（var关键字）示例
 * 
 * JDK10引入了局部变量类型推断，使用var关键字可以让编译器自动推断变量类型。
 * 这使得代码更加简洁，同时保持了类型安全。
 * 
 * var关键字的特点：
 * 1. 只能用于局部变量
 * 2. 必须在声明时初始化
 * 3. 不能用于方法参数、返回类型、字段等
 * 4. 编译器会推断出具体的类型，仍然是强类型的
 * 5. 可以提高代码的可读性（特别是泛型类型很复杂时）
 * 
 * 使用场景：
 * - 简化泛型类型的声明
 * - 提高代码可读性
 * - 减少样板代码
 */
public class VarTypeInferenceExample {

    /**
     * 示例1：基本使用
     * var关键字的基本用法
     */
    public static void example1_BasicUsage() {
        System.out.println("=== 示例1：var关键字基本使用 ===");
        
        // 传统方式
        String name1 = "Hello";
        int age1 = 25;
        
        // 使用var
        var name2 = "Hello";
        var age2 = 25;
        
        System.out.println("传统方式 - name: " + name1 + ", age: " + age1);
        System.out.println("var方式 - name: " + name2 + ", age: " + age2);
        
        // var推断的类型是确定的
        // name2的类型是String，age2的类型是int
        System.out.println("name2的类型：" + name2.getClass().getSimpleName());
    }

    /**
     * 示例2：简化泛型类型声明
     * var在泛型类型很复杂时特别有用
     */
    public static void example2_GenericTypes() {
        System.out.println("\n=== 示例2：简化泛型类型声明 ===");
        
        // 传统方式：类型声明很长
        Map<String, List<Map<Integer, String>>> complexMap1 = new HashMap<>();
        
        // 使用var：更简洁
        var complexMap2 = new HashMap<String, List<Map<Integer, String>>>();
        
        // 注意：使用var时，右侧必须明确指定泛型类型
        // var map = new HashMap<>(); // 这样会推断为HashMap<Object, Object>
        var map = new HashMap<String, Integer>(); // 正确的方式
        
        System.out.println("传统方式类型：" + complexMap1.getClass().getName());
        System.out.println("var方式类型：" + complexMap2.getClass().getName());
    }

    /**
     * 示例3：在循环中使用var
     * 简化for循环中的类型声明
     */
    public static void example3_InLoops() {
        System.out.println("\n=== 示例3：在循环中使用var ===");
        
        List<String> names = List.of("Alice", "Bob", "Charlie");
        
        // 传统方式
        for (String name : names) {
            System.out.println("传统方式：" + name);
        }
        
        // 使用var
        for (var name : names) {
            System.out.println("var方式：" + name);
        }
        
        // 在索引循环中
        for (var i = 0; i < names.size(); i++) {
            System.out.println("索引：" + i + ", 值：" + names.get(i));
        }
    }

    /**
     * 示例4：与Lambda表达式结合
     * var在Lambda表达式中的使用
     */
    public static void example4_WithLambda() {
        System.out.println("\n=== 示例4：与Lambda表达式结合 ===");
        
        List<String> words = List.of("hello", "world", "java");
        
        // 传统方式
        words.forEach((String word) -> System.out.println(word));
        
        // 使用var（JDK11+才支持在Lambda参数中使用var）
        // 注意：JDK10中var不能用于Lambda参数，JDK11才支持
        words.forEach(word -> System.out.println("var方式：" + word));
        
        // 使用var存储Lambda表达式的结果
        var result = words.stream()
                          .map(String::toUpperCase)
                          .toList();
        System.out.println("转换结果：" + result);
    }

    /**
     * 示例5：实际应用场景
     * 在实际代码中使用var提高可读性
     */
    public static void example5_RealWorldUsage() {
        System.out.println("\n=== 示例5：实际应用场景 ===");
        
        // 场景1：处理复杂的数据结构
        var userMap = new HashMap<String, Map<String, Object>>();
        var userInfo = new HashMap<String, Object>();
        userInfo.put("name", "Alice");
        userInfo.put("age", 25);
        userMap.put("user1", userInfo);
        
        System.out.println("用户信息：" + userMap);
        
        // 场景2：处理集合操作的结果
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var evens = numbers.stream()
                          .filter(n -> n % 2 == 0)
                          .toList();
        System.out.println("偶数：" + evens);
        
        // 场景3：处理异常
        try {
            var result = Integer.parseInt("123");
            System.out.println("解析结果：" + result);
        } catch (NumberFormatException e) { // 注意：var在catch中使用是JDK11的特性
            System.out.println("捕获异常：" + e.getClass().getSimpleName());
        }
    }

    /**
     * 示例6：var的限制
     * 展示var关键字不能使用的场景
     */
    public static void example6_Limitations() {
        System.out.println("\n=== 示例6：var的限制 ===");
        
        // ✅ 可以：局部变量
        var localVar = "Hello";
        
        // ✅ 可以：必须在声明时初始化
        var initialized = 10;
        
        // ❌ 不可以：未初始化
        // var uninitialized; // 编译错误
        
        // ❌ 不可以：null初始化（无法推断类型）
        // var nullVar = null; // 编译错误
        
        // ❌ 不可以：catch子句中使用var（JDK11才支持）
        // catch (var e) { } // JDK10不支持
        
        // ❌ 不可以：方法参数
        // public void method(var param) {} // 编译错误
        
        // ❌ 不可以：返回类型
        // public var method() { return "hello"; } // 编译错误
        
        // ❌ 不可以：字段
        // private var field = "hello"; // 编译错误
        
        System.out.println("var只能用于局部变量，且必须初始化");
    }

    /**
     * 示例7：何时使用var
     * 最佳实践：什么时候应该使用var
     */
    public static void example7_BestPractices() {
        System.out.println("\n=== 示例7：何时使用var ===");
        
        // ✅ 推荐：类型很明显时
        var name = "Alice";
        var age = 25;
        var list = new ArrayList<String>();
        
        // ✅ 推荐：泛型类型很复杂时
        var complexMap = new HashMap<String, List<Map<Integer, String>>>();
        
        // ✅ 推荐：提高可读性时
        var result = processData();
        System.out.println("处理结果：" + result);
        
        // ⚠️ 谨慎：类型不明显时，可能降低可读性
        // var data = getData(); // 如果getData()返回类型不明确，可能降低可读性
        
        System.out.println("建议：在类型明显或能提高可读性时使用var");
    }
    
    private static String processData() {
        return "处理后的数据";
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK10 局部变量类型推断（var关键字）示例 ==========\n");
        
        example1_BasicUsage();
        example2_GenericTypes();
        example3_InLoops();
        example4_WithLambda();
        example5_RealWorldUsage();
        example6_Limitations();
        example7_BestPractices();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

