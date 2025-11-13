package com.ibsrapp.jdk11.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * JDK11 Files增强示例
 * 
 * JDK11为Files类添加了便捷的文件读写方法：
 * 1. readString() - 读取文件内容为字符串
 * 2. writeString() - 将字符串写入文件
 * 
 * 这些方法大大简化了文件读写操作，不需要手动管理流。
 */
public class FilesEnhancementExample {

    /**
     * 示例1：readString()方法
     * 读取文件内容为字符串
     */
    public static void example1_ReadString() {
        System.out.println("=== 示例1：readString()方法 ===");
        
        try {
            // 创建临时文件
            Path tempFile = Files.createTempFile("test", ".txt");
            Files.writeString(tempFile, "Hello World\n这是第二行\n这是第三行");
            
            // 使用readString()读取文件
            String content = Files.readString(tempFile);
            System.out.println("文件内容：\n" + content);
            
            // 指定字符集
            String contentUtf8 = Files.readString(tempFile, java.nio.charset.StandardCharsets.UTF_8);
            System.out.println("UTF-8编码读取：\n" + contentUtf8);
            
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        } catch (IOException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    /**
     * 示例2：writeString()方法
     * 将字符串写入文件
     */
    public static void example2_WriteString() {
        System.out.println("\n=== 示例2：writeString()方法 ===");
        
        try {
            // 创建临时文件
            Path tempFile = Files.createTempFile("write", ".txt");
            
            // 写入字符串
            Files.writeString(tempFile, "这是第一行\n这是第二行\n这是第三行");
            System.out.println("文件写入成功");
            
            // 读取验证
            String content = Files.readString(tempFile);
            System.out.println("读取的内容：\n" + content);
            
            // 追加内容
            Files.writeString(tempFile, "\n这是追加的内容", StandardOpenOption.APPEND);
            System.out.println("\n追加后的内容：\n" + Files.readString(tempFile));
            
            // 清理临时文件
            Files.deleteIfExists(tempFile);
        } catch (IOException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    /**
     * 示例3：实际应用场景
     * 使用新方法处理配置文件
     */
    public static void example3_RealWorldUsage() {
        System.out.println("\n=== 示例3：实际应用场景 ===");
        
        try {
            // 创建配置文件
            Path configFile = Files.createTempFile("config", ".properties");
            
            // 写入配置
            String config = """
                # 应用配置
                app.name=MyApp
                app.version=1.0.0
                database.url=jdbc:mysql://localhost:3306/mydb
                database.username=admin
                """;
            Files.writeString(configFile, config);
            
            // 读取配置
            String content = Files.readString(configFile);
            System.out.println("配置文件内容：\n" + content);
            
            // 处理配置行
            long configLines = content.lines()
                                     .filter(line -> !line.isBlank())
                                     .filter(line -> !line.startsWith("#"))
                                     .count();
            System.out.println("有效配置项数量：" + configLines);
            
            // 清理临时文件
            Files.deleteIfExists(configFile);
        } catch (IOException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK11 Files增强示例 ==========\n");
        
        example1_ReadString();
        example2_WriteString();
        example3_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

