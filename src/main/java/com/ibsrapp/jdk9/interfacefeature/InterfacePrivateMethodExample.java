package com.ibsrapp.jdk9.interfacefeature;

/**
 * JDK9 接口私有方法示例
 * 
 * JDK9允许在接口中定义私有方法，这使得接口可以更好地组织代码，
 * 将公共默认方法中的重复代码提取到私有方法中。
 * 
 * 接口私有方法的特点：
 * 1. 使用private关键字修饰
 * 2. 可以有方法体
 * 3. 只能被接口内的其他方法调用
 * 4. 不能被子接口或实现类访问
 * 5. 可以是静态的或实例的
 * 
 * 使用场景：
 * - 提取默认方法中的公共逻辑
 * - 减少代码重复
 * - 提高代码的可维护性
 */
public class InterfacePrivateMethodExample {

    /**
     * 示例1：接口私有实例方法
     * 在默认方法中使用私有方法提取公共逻辑
     */
    interface Calculator {
        // 公共默认方法
        default int add(int a, int b) {
            validateInput(a, b);
            return performAdd(a, b);
        }
        
        default int multiply(int a, int b) {
            validateInput(a, b);
            return performMultiply(a, b);
        }
        
        // 私有方法：提取公共的验证逻辑
        private void validateInput(int a, int b) {
            if (a < 0 || b < 0) {
                throw new IllegalArgumentException("输入不能为负数");
            }
        }
        
        // 私有方法：执行加法
        private int performAdd(int a, int b) {
            return a + b;
        }
        
        // 私有方法：执行乘法
        private int performMultiply(int a, int b) {
            return a * b;
        }
    }
    
    static class SimpleCalculator implements Calculator {
        // 实现类不需要实现默认方法，可以直接使用
    }
    
    public static void example1_PrivateInstanceMethod() {
        System.out.println("=== 示例1：接口私有实例方法 ===");
        
        Calculator calc = new SimpleCalculator();
        
        System.out.println("10 + 20 = " + calc.add(10, 20));
        System.out.println("5 * 6 = " + calc.multiply(5, 6));
        
        // 测试验证逻辑
        try {
            calc.add(-1, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获异常：" + e.getMessage());
        }
    }

    /**
     * 示例2：接口私有静态方法
     * 在静态方法中使用私有静态方法
     */
    interface StringProcessor {
        // 公共静态方法
        static String process(String input) {
            String cleaned = cleanInput(input);
            return formatOutput(cleaned);
        }
        
        // 私有静态方法：清理输入
        private static String cleanInput(String input) {
            if (input == null) {
                return "";
            }
            return input.trim();
        }
        
        // 私有静态方法：格式化输出
        private static String formatOutput(String input) {
            return input.toUpperCase();
        }
    }
    
    public static void example2_PrivateStaticMethod() {
        System.out.println("\n=== 示例2：接口私有静态方法 ===");
        
        String result1 = StringProcessor.process("  hello world  ");
        System.out.println("处理结果1：" + result1);
        
        String result2 = StringProcessor.process(null);
        System.out.println("处理结果2：" + result2);
    }

    /**
     * 示例3：私有方法在多个默认方法中共享逻辑
     * 展示如何通过私有方法减少代码重复
     */
    interface Logger {
        default void logInfo(String message) {
            log("INFO", message);
        }
        
        default void logError(String message) {
            log("ERROR", message);
        }
        
        default void logWarning(String message) {
            log("WARNING", message);
        }
        
        // 私有方法：统一的日志记录逻辑
        private void log(String level, String message) {
            String timestamp = getCurrentTimestamp();
            System.out.println(String.format("[%s] [%s] %s", timestamp, level, message));
        }
        
        // 私有方法：获取当前时间戳
        private String getCurrentTimestamp() {
            return java.time.LocalDateTime.now().toString();
        }
    }
    
    static class FileLogger implements Logger {
        // 实现类可以直接使用默认方法
    }
    
    public static void example3_SharedLogic() {
        System.out.println("\n=== 示例3：私有方法共享逻辑 ===");
        
        Logger logger = new FileLogger();
        logger.logInfo("这是一条信息日志");
        logger.logWarning("这是一条警告日志");
        logger.logError("这是一条错误日志");
    }

    /**
     * 示例4：私有方法与默认方法的组合
     * 展示复杂的接口设计
     */
    interface DataValidator {
        default boolean validateEmail(String email) {
            return isValidFormat(email) && containsAtSymbol(email);
        }
        
        default boolean validatePhone(String phone) {
            return isValidFormat(phone) && containsDigits(phone);
        }
        
        // 私有方法：检查格式
        private boolean isValidFormat(String input) {
            return input != null && !input.trim().isEmpty();
        }
        
        // 私有方法：检查是否包含@符号
        private boolean containsAtSymbol(String input) {
            return input.contains("@");
        }
        
        // 私有方法：检查是否包含数字
        private boolean containsDigits(String input) {
            return input.matches(".*\\d+.*");
        }
    }
    
    static class FormValidator implements DataValidator {
        // 实现类可以使用默认方法
    }
    
    public static void example4_ComplexValidation() {
        System.out.println("\n=== 示例4：复杂验证逻辑 ===");
        
        DataValidator validator = new FormValidator();
        
        System.out.println("邮箱验证（有效）：" + validator.validateEmail("test@example.com"));
        System.out.println("邮箱验证（无效）：" + validator.validateEmail("invalid"));
        
        System.out.println("电话验证（有效）：" + validator.validatePhone("123-456-7890"));
        System.out.println("电话验证（无效）：" + validator.validatePhone("abc"));
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK9 接口私有方法示例 ==========\n");
        
        example1_PrivateInstanceMethod();
        example2_PrivateStaticMethod();
        example3_SharedLogic();
        example4_ComplexValidation();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

