package com.ibsrapp.jdk17.sealed;

/**
 * JDK17 Sealed Classes（密封类）示例
 * 
 * JDK17正式引入了Sealed Classes（密封类），允许类或接口限制哪些类可以继承或实现它们。
 * 这提供了对继承层次结构的更好控制。
 * 
 * Sealed Classes特点：
 * 1. 使用sealed关键字定义
 * 2. 使用permits指定允许的子类
 * 3. 子类必须使用final、sealed或non-sealed修饰
 * 4. 提供更好的类型安全
 * 5. 与Pattern Matching结合使用效果更好
 * 
 * 使用场景：
 * - 定义有限的类型层次结构
 * - 实现状态机
 * - 表示代数数据类型
 */
public class SealedClassesExample {

    /**
     * 示例1：基本Sealed Class
     * 定义一个密封类和它的子类
     */
    // 定义密封接口（静态内部接口）
    sealed static interface Shape permits Circle, Rectangle, Triangle {
        double area();
    }
    
    // 允许的子类1：final类（静态内部类）
    final static class Circle implements Shape {
        private final double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }
    
    // 允许的子类2：final类（静态内部类）
    final static class Rectangle implements Shape {
        private final double width, height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double area() {
            return width * height;
        }
    }
    
    // 允许的子类3：final类（静态内部类）
    final static class Triangle implements Shape {
        private final double base, height;
        
        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }
        
        @Override
        public double area() {
            return 0.5 * base * height;
        }
    }
    
    public static void example1_BasicSealedClass() {
        System.out.println("=== 示例1：基本Sealed Class ===");
        
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0);
        
        System.out.println("圆形面积：" + circle.area());
        System.out.println("矩形面积：" + rectangle.area());
        System.out.println("三角形面积：" + triangle.area());
    }

    /**
     * 示例2：Sealed Class与Pattern Matching
     * 结合使用提供类型安全
     */
    public static void example2_WithPatternMatching() {
        System.out.println("\n=== 示例2：Sealed Class与Pattern Matching ===");
        
        Shape shape = new Circle(3.0);
        
        // 使用Pattern Matching处理所有可能的类型
        String description = switch (shape) {
            case Circle c -> "圆形，半径：" + c.radius;
            case Rectangle r -> "矩形，宽：" + r.width + "，高：" + r.height;
            case Triangle t -> "三角形，底：" + t.base + "，高：" + t.height;
            // 编译器知道所有情况都已覆盖
        };
        
        System.out.println(description);
    }

    /**
     * 示例3：Sealed Class层次结构
     * 定义更复杂的层次结构
     */
    // 密封类（静态内部类）
    sealed static class Animal permits Dog, Cat, Bird {
        public void makeSound() {
            System.out.println("动物发出声音");
        }
    }
    
    // final子类（静态内部类）
    final static class Dog extends Animal {
        @Override
        public void makeSound() {
            System.out.println("汪汪");
        }
    }
    
    // sealed子类（可以继续限制，静态内部类）
    sealed static class Bird extends Animal permits Parrot, Sparrow {
        @Override
        public void makeSound() {
            System.out.println("鸟叫");
        }
    }
    
    // Bird的子类（静态内部类）
    final static class Parrot extends Bird {
        @Override
        public void makeSound() {
            System.out.println("鹦鹉说话");
        }
    }
    
    final static class Sparrow extends Bird {
        @Override
        public void makeSound() {
            System.out.println("麻雀叫");
        }
    }
    
    // final子类（静态内部类）
    final static class Cat extends Animal {
        @Override
        public void makeSound() {
            System.out.println("喵喵");
        }
    }
    
    public static void example3_Hierarchy() {
        System.out.println("\n=== 示例3：Sealed Class层次结构 ===");
        
        Animal dog = new Dog();
        Animal cat = new Cat();
        Animal parrot = new Parrot();
        
        dog.makeSound();
        cat.makeSound();
        parrot.makeSound();
    }

    /**
     * 示例4：实际应用场景
     * 使用Sealed Classes实现状态机
     */
    sealed static interface OrderState permits Pending, Processing, Shipped, Delivered, Cancelled {
    }
    
    final static class Pending implements OrderState {}
    final static class Processing implements OrderState {}
    final static class Shipped implements OrderState {}
    final static class Delivered implements OrderState {}
    final static class Cancelled implements OrderState {}
    
    record Order(String id, OrderState state) {
        public Order transitionTo(OrderState newState) {
            return new Order(id, newState);
        }
    }
    
    public static void example4_StateMachine() {
        System.out.println("\n=== 示例4：状态机应用 ===");
        
        Order order = new Order("ORD-001", new Pending());
        System.out.println("初始状态：" + order.state().getClass().getSimpleName());
        
        order = order.transitionTo(new Processing());
        System.out.println("处理后状态：" + order.state().getClass().getSimpleName());
        
        order = order.transitionTo(new Shipped());
        System.out.println("发货后状态：" + order.state().getClass().getSimpleName());
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK17 Sealed Classes示例 ==========\n");
        
        example1_BasicSealedClass();
        example2_WithPatternMatching();
        example3_Hierarchy();
        example4_StateMachine();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

