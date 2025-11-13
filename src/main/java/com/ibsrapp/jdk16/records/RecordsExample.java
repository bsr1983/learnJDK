package com.ibsrapp.jdk16.records;

/**
 * JDK16 Records（记录类）示例
 * 
 * JDK16正式引入了Records（记录类），这是一种简洁的数据载体类。
 * Records自动生成构造函数、getter、equals、hashCode和toString方法。
 * 
 * Records特点：
 * 1. 使用record关键字定义
 * 2. 自动生成标准方法
 * 3. 不可变（final字段）
 * 4. 可以定义自定义方法
 * 5. 可以定义静态字段和方法
 * 6. 可以定义构造函数
 * 
 * 使用场景：
 * - DTO（数据传输对象）
 * - 值对象
 * - 不可变数据容器
 */
public class RecordsExample {

    /**
     * 示例1：基本Record定义
     * 定义一个简单的Record
     */
    // 定义一个Person Record
    record Person(String name, int age) {
    }
    
    public static void example1_BasicRecord() {
        System.out.println("=== 示例1：基本Record定义 ===");
        
        // 创建Record实例
        Person person = new Person("Alice", 25);
        
        // 自动生成的getter方法（没有get前缀）
        System.out.println("姓名：" + person.name());
        System.out.println("年龄：" + person.age());
        
        // 自动生成的toString方法
        System.out.println("Person：" + person);
        
        // 自动生成的equals方法
        Person person2 = new Person("Alice", 25);
        System.out.println("person.equals(person2)：" + person.equals(person2));
        
        // 自动生成的hashCode方法
        System.out.println("person.hashCode()：" + person.hashCode());
    }

    /**
     * 示例2：自定义方法
     * 在Record中定义自定义方法
     */
    record Point(int x, int y) {
        // 自定义方法
        public double distanceFromOrigin() {
            return Math.sqrt(x * x + y * y);
        }
        
        // 静态方法
        public static Point origin() {
            return new Point(0, 0);
        }
    }
    
    public static void example2_CustomMethods() {
        System.out.println("\n=== 示例2：自定义方法 ===");
        
        Point p1 = new Point(3, 4);
        System.out.println("点：" + p1);
        System.out.println("到原点的距离：" + p1.distanceFromOrigin());
        
        Point origin = Point.origin();
        System.out.println("原点：" + origin);
    }

    /**
     * 示例3：自定义构造函数
     * 在Record中定义自定义构造函数
     */
    record Email(String address) {
        // 紧凑构造函数（在字段赋值后执行）
        public Email {
            if (address == null || !address.contains("@")) {
                throw new IllegalArgumentException("无效的邮箱地址");
            }
        }
        
        // 也可以定义完整构造函数
        // public Email(String address) {
        //     this.address = address.toLowerCase();
        // }
    }
    
    public static void example3_CustomConstructor() {
        System.out.println("\n=== 示例3：自定义构造函数 ===");
        
        Email email = new Email("test@example.com");
        System.out.println("邮箱：" + email.address());
        
        try {
            new Email("invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("验证失败：" + e.getMessage());
        }
    }

    /**
     * 示例4：嵌套Record
     * Record可以嵌套使用
     */
    record Address(String street, String city, String zipCode) {}
    
    record Employee(String name, int age, Address address) {}
    
    public static void example4_NestedRecords() {
        System.out.println("\n=== 示例4：嵌套Record ===");
        
        Address addr = new Address("123 Main St", "Beijing", "100000");
        Employee emp = new Employee("Bob", 30, addr);
        
        System.out.println("员工：" + emp.name());
        System.out.println("地址：" + emp.address().city());
        System.out.println("完整信息：" + emp);
    }

    /**
     * 示例5：实际应用场景
     * 在业务逻辑中使用Records
     */
    record User(String username, String email, boolean active) {}
    
    public static void example5_RealWorldUsage() {
        System.out.println("\n=== 示例5：实际应用场景 ===");
        
        // 创建用户
        User user1 = new User("alice", "alice@example.com", true);
        User user2 = new User("bob", "bob@example.com", false);
        
        System.out.println("用户1：" + user1);
        System.out.println("用户2：" + user2);
        
        // 使用模式匹配（JDK16+）
        if (user1 instanceof User(String username, String email, boolean active) && active) {
            System.out.println("活跃用户：" + username);
        }
    }

    /**
     * 示例6：Records vs 传统类
     * 对比Records和传统类的区别
     */
    // 传统方式：需要大量样板代码
    static class TraditionalPerson {
        private final String name;
        private final int age;
        
        public TraditionalPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TraditionalPerson that = (TraditionalPerson) o;
            return age == that.age && name.equals(that.name);
        }
        
        @Override
        public int hashCode() {
            return name.hashCode() * 31 + age;
        }
        
        @Override
        public String toString() {
            return "TraditionalPerson{name='" + name + "', age=" + age + "}";
        }
    }
    
    public static void example6_Comparison() {
        System.out.println("\n=== 示例6：Records vs 传统类 ===");
        
        // Record方式：简洁
        Person person = new Person("Alice", 25);
        
        // 传统方式：冗长
        TraditionalPerson traditionalPerson = new TraditionalPerson("Alice", 25);
        
        System.out.println("Record：" + person);
        System.out.println("传统类：" + traditionalPerson);
        System.out.println("Records大大减少了样板代码");
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK16 Records示例 ==========\n");
        
        example1_BasicRecord();
        example2_CustomMethods();
        example3_CustomConstructor();
        example4_NestedRecords();
        example5_RealWorldUsage();
        example6_Comparison();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

