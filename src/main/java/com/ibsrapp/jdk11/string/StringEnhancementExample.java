package com.ibsrapp.jdk11.string;

import java.util.stream.Collectors;

/**
 * JDK11 字符串增强示例
 * 
 * JDK11为String类添加了多个实用的方法，使字符串处理更加方便：
 * 1. strip() - 去除首尾空白字符（比trim()更强大，支持Unicode）
 * 2. stripLeading() - 去除前导空白字符
 * 3. stripTrailing() - 去除尾部空白字符
 * 4. isBlank() - 判断字符串是否为空或只包含空白字符
 * 5. lines() - 将字符串按行分割成Stream
 * 6. repeat() - 重复字符串指定次数
 * 
 * 这些方法使得字符串处理更加简洁和高效。
 */
public class StringEnhancementExample {

    /**
     * 示例1：strip()方法
     * strip()比trim()更强大，支持Unicode空白字符
     */
    public static void example1_Strip() {
        System.out.println("=== 示例1：strip()方法 ===");
        
        String str1 = "  hello world  ";
        String str2 = "\u2000  hello world  \u2000"; // 包含Unicode空白字符
        
        // trim()只能去除ASCII空白字符
        System.out.println("trim()结果：'" + str1.trim() + "'");
        System.out.println("trim()结果（Unicode）：'" + str2.trim() + "'");
        
        // strip()可以去除所有Unicode空白字符
        System.out.println("strip()结果：'" + str1.strip() + "'");
        System.out.println("strip()结果（Unicode）：'" + str2.strip() + "'");
        
        // stripLeading()和stripTrailing()
        String str3 = "  hello world  ";
        System.out.println("stripLeading()：'" + str3.stripLeading() + "'");
        System.out.println("stripTrailing()：'" + str3.stripTrailing() + "'");
    }

    /**
     * 示例2：isBlank()方法
     * 判断字符串是否为空或只包含空白字符
     */
    public static void example2_IsBlank() {
        System.out.println("\n=== 示例2：isBlank()方法 ===");
        
        String str1 = "";
        String str2 = "   ";
        String str3 = "\t\n\r";
        String str4 = "hello";
        
        System.out.println("''.isBlank()：" + str1.isBlank());
        System.out.println("'   '.isBlank()：" + str2.isBlank());
        System.out.println("'\\t\\n\\r'.isBlank()：" + str3.isBlank());
        System.out.println("'hello'.isBlank()：" + str4.isBlank());
        
        // 与isEmpty()的区别
        System.out.println("''.isEmpty()：" + str1.isEmpty());
        System.out.println("'   '.isEmpty()：" + str2.isEmpty()); // false，因为不是空字符串
    }

    /**
     * 示例3：lines()方法
     * 将字符串按行分割成Stream，便于处理多行文本
     */
    public static void example3_Lines() {
        System.out.println("\n=== 示例3：lines()方法 ===");
        
        String text = "第一行\n第二行\n第三行\n第四行";
        
        // 使用lines()获取行数
        long lineCount = text.lines().count();
        System.out.println("行数：" + lineCount);
        
        // 处理每一行
        System.out.println("处理每一行：");
        text.lines().forEach(line -> System.out.println("  - " + line));
        
        // 过滤和转换
        String filtered = text.lines()
                              .filter(line -> line.contains("三"))
                              .collect(Collectors.joining("\n"));
        System.out.println("包含'三'的行：" + filtered);
        
        // 转换为大写
        String upperCase = text.lines()
                               .map(String::toUpperCase)
                               .collect(Collectors.joining("\n"));
        System.out.println("转换为大写：\n" + upperCase);
    }

    /**
     * 示例4：repeat()方法
     * 重复字符串指定次数
     */
    public static void example4_Repeat() {
        System.out.println("\n=== 示例4：repeat()方法 ===");
        
        String str = "hello";
        
        // 重复3次
        String repeated = str.repeat(3);
        System.out.println("重复3次：" + repeated);
        
        // 重复0次返回空字符串
        System.out.println("重复0次：'" + str.repeat(0) + "'");
        
        // 实际应用：创建分隔线
        String separator = "-".repeat(50);
        System.out.println(separator);
        System.out.println("这是一条分隔线");
        System.out.println(separator);
        
        // 创建缩进
        String indent = " ".repeat(4);
        System.out.println(indent + "缩进4个空格");
    }

    /**
     * 示例5：实际应用场景
     * 组合使用这些新方法处理文本
     */
    public static void example5_RealWorldUsage() {
        System.out.println("\n=== 示例5：实际应用场景 ===");
        
        // 场景1：处理用户输入
        String userInput = "  hello world  ";
        String cleaned = userInput.strip();
        if (!cleaned.isBlank()) {
            System.out.println("处理后的输入：'" + cleaned + "'");
        }
        
        // 场景2：处理多行文本
        String multiLineText = "  第一行  \n  第二行  \n  第三行  ";
        String processed = multiLineText.lines()
                                        .map(String::strip)
                                        .filter(line -> !line.isBlank())
                                        .collect(Collectors.joining("\n"));
        System.out.println("处理后的多行文本：\n" + processed);
        
        // 场景3：格式化输出
        String title = "标题";
        String formattedTitle = "=".repeat(20) + " " + title + " " + "=".repeat(20);
        System.out.println(formattedTitle);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK11 字符串增强示例 ==========\n");
        
        example1_Strip();
        example2_IsBlank();
        example3_Lines();
        example4_Repeat();
        example5_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

