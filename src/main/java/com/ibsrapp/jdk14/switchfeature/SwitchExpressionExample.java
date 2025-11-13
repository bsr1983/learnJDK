package com.ibsrapp.jdk14.switchfeature;

/**
 * JDK14 Switch表达式示例
 * 
 * JDK14正式引入了Switch表达式，这是对传统switch语句的重大改进：
 * 1. 可以作为表达式使用，返回值
 * 2. 使用箭头语法（->），不需要break
 * 3. 支持多个值匹配（case "a", "b":）
 * 4. 支持yield关键字返回值
 * 5. 更简洁、更安全、更易读
 * 
 * Switch表达式特点：
 * - 不会出现fall-through（穿透）问题
 * - 必须覆盖所有可能的情况（或使用default）
 * - 可以作为表达式使用，赋值给变量
 */
public class SwitchExpressionExample {

    /**
     * 示例1：基本Switch表达式
     * 使用箭头语法和返回值
     */
    public static void example1_BasicSwitchExpression() {
        System.out.println("=== 示例1：基本Switch表达式 ===");
        
        String day = "MONDAY";
        
        // Switch表达式：直接返回值
        int dayNumber = switch (day) {
            case "MONDAY" -> 1;
            case "TUESDAY" -> 2;
            case "WEDNESDAY" -> 3;
            case "THURSDAY" -> 4;
            case "FRIDAY" -> 5;
            case "SATURDAY" -> 6;
            case "SUNDAY" -> 7;
            default -> 0;
        };
        
        System.out.println(day + " 对应数字：" + dayNumber);
        
        // 多个值匹配
        String type = "apple";
        String category = switch (type) {
            case "apple", "banana", "orange" -> "水果";
            case "carrot", "potato" -> "蔬菜";
            default -> "未知";
        };
        System.out.println(type + " 类别：" + category);
    }

    /**
     * 示例2：使用yield返回值
     * 在需要多行代码时使用yield
     */
    public static void example2_Yield() {
        System.out.println("\n=== 示例2：使用yield返回值 ===");
        
        int score = 85;
        
        String grade = switch (score / 10) {
            case 10, 9 -> {
                System.out.println("优秀");
                yield "A";
            }
            case 8 -> {
                System.out.println("良好");
                yield "B";
            }
            case 7 -> {
                System.out.println("中等");
                yield "C";
            }
            case 6 -> {
                System.out.println("及格");
                yield "D";
            }
            default -> {
                System.out.println("不及格");
                yield "F";
            }
        };
        
        System.out.println("分数：" + score + "，等级：" + grade);
    }

    /**
     * 示例3：Switch表达式 vs 传统Switch语句
     * 对比两种方式的区别
     */
    public static void example3_Comparison() {
        System.out.println("\n=== 示例3：Switch表达式 vs 传统Switch语句 ===");
        
        String status = "ACTIVE";
        
        // 传统Switch语句
        String message1;
        switch (status) {
            case "ACTIVE":
                message1 = "激活";
                break;
            case "INACTIVE":
                message1 = "未激活";
                break;
            case "PENDING":
                message1 = "待处理";
                break;
            default:
                message1 = "未知";
        }
        System.out.println("传统方式：" + message1);
        
        // Switch表达式（更简洁）
        String message2 = switch (status) {
            case "ACTIVE" -> "激活";
            case "INACTIVE" -> "未激活";
            case "PENDING" -> "待处理";
            default -> "未知";
        };
        System.out.println("Switch表达式：" + message2);
    }

    /**
     * 示例4：实际应用场景
     * 在业务逻辑中使用Switch表达式
     */
    public static void example4_RealWorldUsage() {
        System.out.println("\n=== 示例4：实际应用场景 ===");
        
        // 场景1：状态转换
        String currentStatus = "DRAFT";
        String nextStatus = switch (currentStatus) {
            case "DRAFT" -> "REVIEW";
            case "REVIEW" -> "APPROVED";
            case "APPROVED" -> "PUBLISHED";
            default -> currentStatus;
        };
        System.out.println("当前状态：" + currentStatus + "，下一状态：" + nextStatus);
        
        // 场景2：计算价格
        String productType = "PREMIUM";
        double basePrice = 100.0;
        double finalPrice = basePrice * switch (productType) {
            case "BASIC" -> 1.0;
            case "STANDARD" -> 1.2;
            case "PREMIUM" -> 1.5;
            default -> 1.0;
        };
        System.out.println("基础价格：" + basePrice + "，最终价格：" + finalPrice);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK14 Switch表达式示例 ==========\n");
        
        example1_BasicSwitchExpression();
        example2_Yield();
        example3_Comparison();
        example4_RealWorldUsage();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

