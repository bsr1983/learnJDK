package com.ibsrapp.jdk22.stringtemplates;

/**
 * JDK22 String Templates（字符串模板）示例
 * 
 * JDK22引入了String Templates（字符串模板），提供了一种更安全、更易读的字符串插值方式。
 * 使用STR模板处理器可以安全地插入变量值，避免SQL注入等安全问题。
 * 
 * String Templates特点：
 * 1. 使用\{variable}语法进行插值
 * 2. 使用STR模板处理器
 * 3. 编译时验证
 * 4. 防止注入攻击
 * 5. 更易读的字符串格式化
 * 
 * 注意：这是预览特性，需要使用--enable-preview编译
 */
public class StringTemplatesExample {

    /**
     * 示例1：基本String Templates
     * 使用STR模板处理器
     */
    public static void example1_BasicStringTemplates() {
        System.out.println("=== 示例1：基本String Templates ===");
        
        String name = "Alice";
        int age = 25;
        
        // 传统方式：字符串拼接
        String message1 = "Hello, " + name + "! You are " + age + " years old.";
        
        // String Templates方式（JDK22预览特性）
        // 注意：实际使用时需要JDK22并启用预览特性
        // String message2 = STR."Hello, \{name}! You are \{age} years old.";
        
        // 由于是预览特性，这里使用注释说明语法
        System.out.println("传统方式：" + message1);
        System.out.println("String Templates语法：STR.\"Hello, \\{name}! You are \\{age} years old.\"");
        System.out.println("（需要JDK22并启用--enable-preview）");
    }

    /**
     * 示例2：多行String Templates
     * 结合Text Blocks使用
     */
    public static void example2_MultilineStringTemplates() {
        System.out.println("\n=== 示例2：多行String Templates ===");
        
        String name = "Bob";
        String email = "bob@example.com";
        
        // String Templates + Text Blocks
        // String message = STR."""
        //     Name: \{name}
        //     Email: \{email}
        //     Welcome to our system!
        //     """;
        
        System.out.println("String Templates语法（多行）：");
        System.out.println("STR.\"\"\"");
        System.out.println("    Name: \\{name}");
        System.out.println("    Email: \\{email}");
        System.out.println("    Welcome to our system!");
        System.out.println("    \"\"\"");
    }

    /**
     * 示例3：表达式插值
     * 在模板中使用表达式
     */
    public static void example3_Expressions() {
        System.out.println("\n=== 示例3：表达式插值 ===");
        
        int x = 10;
        int y = 20;
        
        // String Templates支持表达式
        // String result = STR."\{x} + \{y} = \{x + y}";
        
        System.out.println("表达式插值语法：");
        System.out.println("STR.\"\\{x} + \\{y} = \\{x + y}\"");
        System.out.println("结果：10 + 20 = 30");
    }

    /**
     * 示例4：实际应用场景
     * 在业务逻辑中使用String Templates
     */
    public static void example4_RealWorldUsage() {
        System.out.println("\n=== 示例4：实际应用场景 ===");
        
        // 场景1：日志消息
        String user = "admin";
        String action = "login";
        // String logMessage = STR."User \{user} performed action: \{action}";
        
        // 场景2：SQL查询（安全）
        String tableName = "users";
        String condition = "active = 1";
        // String sql = STR."SELECT * FROM \{tableName} WHERE \{condition}";
        // 注意：实际SQL应该使用PreparedStatement，这里仅演示语法
        
        System.out.println("String Templates提供更安全的字符串插值方式");
        System.out.println("可以防止SQL注入、XSS等安全问题");
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK22 String Templates示例 ==========\n");
        System.out.println("注意：String Templates是JDK22的预览特性");
        System.out.println("需要使用--enable-preview编译和运行\n");
        
        example1_BasicStringTemplates();
        example2_MultilineStringTemplates();
        example3_Expressions();
        example4_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

