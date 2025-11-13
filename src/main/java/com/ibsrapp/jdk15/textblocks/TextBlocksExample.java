package com.ibsrapp.jdk15.textblocks;

/**
 * JDK15 Text Blocks（文本块）示例
 * 
 * JDK15正式引入了Text Blocks（文本块），使用三重引号（"""）来定义多行字符串。
 * 这使得编写多行字符串、SQL、JSON、HTML等更加方便和易读。
 * 
 * Text Blocks特点：
 * 1. 使用"""开始和结束
 * 2. 自动处理缩进和换行
 * 3. 支持转义字符
 * 4. 可以格式化字符串（stripIndent、formatted等）
 * 5. 提高代码可读性
 */
public class TextBlocksExample {

    /**
     * 示例1：基本Text Blocks
     * 创建多行字符串
     */
    public static void example1_BasicTextBlocks() {
        System.out.println("=== 示例1：基本Text Blocks ===");
        
        // 传统方式：使用字符串拼接
        String oldWay = "第一行\n" +
                       "第二行\n" +
                       "第三行";
        
        // Text Blocks方式：更清晰
        String newWay = """
            第一行
            第二行
            第三行
            """;
        
        System.out.println("传统方式：\n" + oldWay);
        System.out.println("Text Blocks方式：\n" + newWay);
    }

    /**
     * 示例2：格式化字符串
     * 在Text Blocks中使用格式化
     */
    public static void example2_Formatted() {
        System.out.println("\n=== 示例2：格式化字符串 ===");
        
        String name = "Alice";
        int age = 25;
        
        String message = """
            Hello, %s!
            You are %d years old.
            Welcome to our system.
            """.formatted(name, age);
        
        System.out.println(message);
    }

    /**
     * 示例3：SQL查询
     * 使用Text Blocks编写SQL
     */
    public static void example3_SQL() {
        System.out.println("\n=== 示例3：SQL查询 ===");
        
        String tableName = "users";
        String sql = """
            SELECT id, name, email
            FROM %s
            WHERE status = 'ACTIVE'
            ORDER BY name
            LIMIT 10
            """.formatted(tableName);
        
        System.out.println("SQL查询：\n" + sql);
    }

    /**
     * 示例4：JSON格式
     * 使用Text Blocks创建JSON
     */
    public static void example4_JSON() {
        System.out.println("\n=== 示例4：JSON格式 ===");
        
        String json = """
            {
                "name": "Alice",
                "age": 25,
                "city": "Beijing",
                "hobbies": ["reading", "coding"]
            }
            """;
        
        System.out.println("JSON：\n" + json);
    }

    /**
     * 示例5：HTML模板
     * 使用Text Blocks创建HTML
     */
    public static void example5_HTML() {
        System.out.println("\n=== 示例5：HTML模板 ===");
        
        String title = "欢迎";
        String content = "这是内容";
        
        String html = """
            <html>
                <head>
                    <title>%s</title>
                </head>
                <body>
                    <h1>%s</h1>
                    <p>%s</p>
                </body>
            </html>
            """.formatted(title, title, content);
        
        System.out.println("HTML：\n" + html);
    }

    /**
     * 示例6：缩进处理
     * Text Blocks的缩进规则
     */
    public static void example6_Indentation() {
        System.out.println("\n=== 示例6：缩进处理 ===");
        
        // 结束引号的位置决定最小缩进
        String text1 = """
            Line 1
            Line 2
            Line 3
            """; // 结束引号在最左边，保留所有缩进
        
        String text2 = """
            Line 1
            Line 2
            Line 3
        """; // 结束引号有缩进，会去除相应缩进
        
        System.out.println("text1：\n" + text1);
        System.out.println("text2：\n" + text2);
    }

    /**
     * 示例7：转义字符
     * 在Text Blocks中使用转义字符
     */
    public static void example7_EscapeSequences() {
        System.out.println("\n=== 示例7：转义字符 ===");
        
        // 使用\s保留尾随空格
        String text = """
            Line 1\s
            Line 2\s
            Line 3
            """;
        
        System.out.println("带尾随空格：\n" + text);
        
        // 使用\n显式换行
        String explicitNewline = """
            First line\nSecond line\nThird line
            """;
        System.out.println("显式换行：\n" + explicitNewline);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK15 Text Blocks示例 ==========\n");
        
        example1_BasicTextBlocks();
        example2_Formatted();
        example3_SQL();
        example4_JSON();
        example5_HTML();
        example6_Indentation();
        example7_EscapeSequences();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

