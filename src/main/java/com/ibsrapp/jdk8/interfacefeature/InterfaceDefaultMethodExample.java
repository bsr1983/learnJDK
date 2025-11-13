package com.ibsrapp.jdk8.interfacefeature;

/**
 * JDK8 接口默认方法和静态方法示例
 * 
 * JDK8之前，接口只能包含抽象方法，所有实现类都必须实现这些方法。
 * JDK8引入了接口的默认方法（default method）和静态方法（static method），
 * 这使得接口的功能更加强大和灵活。
 * 
 * 默认方法的特点：
 * 1. 使用default关键字修饰
 * 2. 可以有方法体
 * 3. 实现类可以选择重写或直接使用
 * 4. 主要用于向后兼容，在不破坏现有实现的情况下为接口添加新方法
 * 
 * 静态方法的特点：
 * 1. 使用static关键字修饰
 * 2. 属于接口本身，不属于实现类
 * 3. 通过接口名直接调用
 * 4. 不能被子接口或实现类继承
 */
public class InterfaceDefaultMethodExample {

    /**
     * 示例1：接口默认方法
     * 定义包含默认方法的接口
     */
    interface Vehicle {
        // 抽象方法：必须被实现
        void start();
        
        // 默认方法：可以有默认实现
        default void stop() {
            System.out.println("车辆停止");
        }
        
        // 另一个默认方法
        default void honk() {
            System.out.println("车辆鸣笛：嘟嘟嘟");
        }
    }
    
    static class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("汽车启动");
        }
        
        // 可以选择重写默认方法
        @Override
        public void honk() {
            System.out.println("汽车鸣笛：滴滴滴");
        }
    }
    
    static class Bike implements Vehicle {
        @Override
        public void start() {
            System.out.println("自行车开始骑行");
        }
        
        // 不重写默认方法，使用接口的默认实现
    }
    
    public static void example1_DefaultMethod() {
        System.out.println("=== 示例1：接口默认方法 ===");
        
        Vehicle car = new Car();
        car.start();
        car.stop(); // 使用默认实现
        car.honk(); // 使用重写的实现
        
        Vehicle bike = new Bike();
        bike.start();
        bike.stop(); // 使用默认实现
        bike.honk(); // 使用默认实现
    }

    /**
     * 示例2：接口静态方法
     * 定义包含静态方法的接口
     */
    interface Calculator {
        // 抽象方法
        int calculate(int a, int b);
        
        // 静态方法：属于接口本身
        static int add(int a, int b) {
            return a + b;
        }
        
        static int multiply(int a, int b) {
            return a * b;
        }
    }
    
    static class AdditionCalculator implements Calculator {
        @Override
        public int calculate(int a, int b) {
            return Calculator.add(a, b); // 调用接口的静态方法
        }
    }
    
    public static void example2_StaticMethod() {
        System.out.println("\n=== 示例2：接口静态方法 ===");
        
        // 直接通过接口名调用静态方法
        int sum = Calculator.add(10, 20);
        int product = Calculator.multiply(5, 6);
        
        System.out.println("10 + 20 = " + sum);
        System.out.println("5 * 6 = " + product);
        
        // 实现类使用静态方法
        Calculator calc = new AdditionCalculator();
        System.out.println("计算结果：" + calc.calculate(15, 25));
    }

    /**
     * 示例3：多重继承问题
     * 当实现多个接口且都有相同的默认方法时，需要解决冲突
     */
    interface Flyable {
        default void move() {
            System.out.println("飞行");
        }
    }
    
    interface Swimmable {
        default void move() {
            System.out.println("游泳");
        }
    }
    
    // 方式1：重写方法解决冲突
    static class Duck1 implements Flyable, Swimmable {
        @Override
        public void move() {
            System.out.println("鸭子可以飞行和游泳");
        }
    }
    
    // 方式2：指定使用哪个接口的默认方法
    static class Duck2 implements Flyable, Swimmable {
        @Override
        public void move() {
            Flyable.super.move(); // 使用Flyable接口的默认方法
        }
    }
    
    public static void example3_MultipleInheritance() {
        System.out.println("\n=== 示例3：多重继承问题 ===");
        
        Duck1 duck1 = new Duck1();
        duck1.move();
        
        Duck2 duck2 = new Duck2();
        duck2.move();
    }

    /**
     * 示例4：实际应用场景
     * 为现有接口添加新方法而不破坏现有实现
     */
    interface ListProcessor {
        void process();
        
        // 新增的默认方法，不影响现有实现
        default void log() {
            System.out.println("处理日志：" + getClass().getSimpleName());
        }
        
        // 新增的静态工具方法
        static void printInfo() {
            System.out.println("这是一个列表处理器接口");
        }
    }
    
    static class OldProcessor implements ListProcessor {
        @Override
        public void process() {
            System.out.println("旧处理器处理数据");
        }
        // 不需要实现log()方法，可以使用默认实现
    }
    
    static class NewProcessor implements ListProcessor {
        @Override
        public void process() {
            System.out.println("新处理器处理数据");
        }
        
        @Override
        public void log() {
            System.out.println("新处理器的自定义日志");
        }
    }
    
    public static void example4_RealWorldUsage() {
        System.out.println("\n=== 示例4：实际应用场景 ===");
        
        ListProcessor old = new OldProcessor();
        old.process();
        old.log(); // 使用默认实现
        
        ListProcessor newProc = new NewProcessor();
        newProc.process();
        newProc.log(); // 使用重写的实现
        
        // 调用接口的静态方法
        ListProcessor.printInfo();
    }

    /**
     * 示例5：接口的默认方法与抽象类的区别
     * 虽然接口可以有默认方法，但接口和抽象类仍有区别
     */
    interface Configurable {
        // 接口可以有常量
        String DEFAULT_CONFIG = "default";
        
        // 抽象方法
        void configure();
        
        // 默认方法
        default void reset() {
            configure(); // 可以调用抽象方法
            System.out.println("重置配置");
        }
        
        // 静态方法
        static String getDefaultConfig() {
            return DEFAULT_CONFIG;
        }
    }
    
    // 注意：接口不能有实例变量、构造器、非public方法等
    // 这些特性是抽象类才有的
    
    public static void example5_InterfaceVsAbstractClass() {
        System.out.println("\n=== 示例5：接口的默认方法与抽象类 ===");
        
        System.out.println("接口常量：" + Configurable.DEFAULT_CONFIG);
        System.out.println("静态方法：" + Configurable.getDefaultConfig());
        
        Configurable config = new Configurable() {
            @Override
            public void configure() {
                System.out.println("配置完成");
            }
        };
        
        config.configure();
        config.reset();
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 接口默认方法和静态方法示例 ==========\n");
        
        example1_DefaultMethod();
        example2_StaticMethod();
        example3_MultipleInheritance();
        example4_RealWorldUsage();
        example5_InterfaceVsAbstractClass();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

