package com.ibsrapp.jdk8.optional;

import java.util.Optional;

/**
 * JDK8 Optional示例
 * 
 * Optional是JDK8引入的一个容器类，用于表示一个值可能存在也可能不存在。
 * 它的主要目的是解决空指针异常（NullPointerException）的问题。
 * 
 * Optional的特点：
 * 1. 明确表示值可能不存在
 * 2. 强制调用者处理空值情况
 * 3. 提供丰富的API来处理可能为空的值
 * 4. 避免显式的null检查
 * 
 * 使用场景：
 * - 方法返回值可能为null时，使用Optional包装
 * - 避免多层null检查
 * - 提供更安全的链式调用
 */
public class OptionalExample {

    /**
     * 示例1：创建Optional对象
     * 有多种方式创建Optional
     */
    public static void example1_CreateOptional() {
        System.out.println("=== 示例1：创建Optional对象 ===");
        
        // 方式1：创建一个包含值的Optional
        Optional<String> optional1 = Optional.of("Hello");
        System.out.println("Optional.of(\"Hello\")：" + optional1);
        
        // 方式2：创建一个可能为空的Optional
        Optional<String> optional2 = Optional.ofNullable(null);
        System.out.println("Optional.ofNullable(null)：" + optional2);
        
        // 方式3：创建一个空的Optional
        Optional<String> optional3 = Optional.empty();
        System.out.println("Optional.empty()：" + optional3);
        
        // 注意：Optional.of(null)会抛出NullPointerException
        // Optional<String> optional4 = Optional.of(null); // 会抛出异常
    }

    /**
     * 示例2：检查值是否存在
     * 使用isPresent()和isEmpty()方法
     */
    public static void example2_CheckValue() {
        System.out.println("\n=== 示例2：检查值是否存在 ===");
        
        Optional<String> present = Optional.of("World");
        Optional<String> empty = Optional.empty();
        
        System.out.println("present.isPresent()：" + present.isPresent());
        System.out.println("present.isEmpty()：" + present.isEmpty());
        System.out.println("empty.isPresent()：" + empty.isPresent());
        System.out.println("empty.isEmpty()：" + empty.isEmpty());
    }

    /**
     * 示例3：获取值
     * 使用get()、orElse()、orElseGet()、orElseThrow()方法
     */
    public static void example3_GetValue() {
        System.out.println("\n=== 示例3：获取值 ===");
        
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        // get()：如果值存在则返回，否则抛出NoSuchElementException
        System.out.println("present.get()：" + present.get());
        // System.out.println(empty.get()); // 会抛出异常
        
        // orElse()：如果值存在则返回，否则返回默认值
        String value1 = present.orElse("默认值");
        String value2 = empty.orElse("默认值");
        System.out.println("present.orElse(\"默认值\")：" + value1);
        System.out.println("empty.orElse(\"默认值\")：" + value2);
        
        // orElseGet()：如果值存在则返回，否则通过Supplier提供默认值（惰性求值）
        String value3 = empty.orElseGet(() -> "通过Supplier提供的默认值");
        System.out.println("empty.orElseGet(...)：" + value3);
        
        // orElseThrow()：如果值存在则返回，否则抛出异常
        try {
            String value4 = empty.orElseThrow(() -> new RuntimeException("值不存在"));
        } catch (RuntimeException e) {
            System.out.println("orElseThrow()抛出异常：" + e.getMessage());
        }
    }

    /**
     * 示例4：条件处理
     * 使用ifPresent()和ifPresentOrElse()方法
     */
    public static void example4_ConditionalProcessing() {
        System.out.println("\n=== 示例4：条件处理 ===");
        
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        // ifPresent()：如果值存在则执行操作
        present.ifPresent(value -> System.out.println("值存在：" + value));
        empty.ifPresent(value -> System.out.println("这行不会执行"));
        
        // ifPresentOrElse()：如果值存在则执行一个操作，否则执行另一个操作
        present.ifPresentOrElse(
            value -> System.out.println("值存在：" + value),
            () -> System.out.println("值不存在")
        );
        empty.ifPresentOrElse(
            value -> System.out.println("这行不会执行"),
            () -> System.out.println("值不存在，执行替代操作")
        );
    }

    /**
     * 示例5：转换操作
     * 使用map()和flatMap()方法
     */
    public static void example5_Transform() {
        System.out.println("\n=== 示例5：转换操作 ===");
        
        Optional<String> optional = Optional.of("hello");
        Optional<String> empty = Optional.empty();
        
        // map()：如果值存在则进行转换
        Optional<String> upperCase = optional.map(String::toUpperCase);
        System.out.println("map转换：" + upperCase.orElse("空"));
        
        // 如果Optional为空，map不会执行
        Optional<String> emptyMap = empty.map(String::toUpperCase);
        System.out.println("空Optional的map：" + emptyMap.orElse("空"));
        
        // flatMap()：用于Optional嵌套的情况
        Optional<Optional<String>> nested = Optional.of(Optional.of("nested"));
        Optional<String> flattened = nested.flatMap(o -> o);
        System.out.println("flatMap展平：" + flattened.orElse("空"));
    }

    /**
     * 示例6：过滤操作
     * 使用filter()方法
     */
    public static void example6_Filter() {
        System.out.println("\n=== 示例6：过滤操作 ===");
        
        Optional<Integer> optional1 = Optional.of(10);
        Optional<Integer> optional2 = Optional.of(5);
        Optional<Integer> empty = Optional.empty();
        
        // filter()：如果值满足条件则返回Optional，否则返回空Optional
        Optional<Integer> filtered1 = optional1.filter(n -> n > 5);
        Optional<Integer> filtered2 = optional2.filter(n -> n > 5);
        Optional<Integer> filtered3 = empty.filter(n -> n > 5);
        
        System.out.println("10 > 5：" + filtered1.isPresent());
        System.out.println("5 > 5：" + filtered2.isPresent());
        System.out.println("空Optional过滤：" + filtered3.isPresent());
    }

    /**
     * 示例7：实际应用场景
     * 模拟一个查找用户的方法
     */
    public static Optional<String> findUserById(int id) {
        // 模拟数据库查询
        if (id > 0 && id <= 10) {
            return Optional.of("用户" + id);
        }
        return Optional.empty();
    }
    
    public static void example7_RealWorldUsage() {
        System.out.println("\n=== 示例7：实际应用场景 ===");
        
        // 查找存在的用户
        Optional<String> user1 = findUserById(5);
        user1.ifPresent(user -> System.out.println("找到用户：" + user));
        
        // 查找不存在的用户
        Optional<String> user2 = findUserById(99);
        String result = user2.orElse("用户不存在");
        System.out.println("查找结果：" + result);
        
        // 链式调用
        findUserById(3)
            .map(String::toUpperCase)
            .filter(name -> name.length() > 5)
            .ifPresent(name -> System.out.println("处理后的用户名：" + name));
    }

    /**
     * 示例8：避免空指针异常
     * 对比使用Optional和传统null检查的区别
     */
    public static String getCityTraditional(User user) {
        // 传统方式：需要多层null检查
        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                return address.getCity();
            }
        }
        return "未知城市";
    }
    
    public static String getCityWithOptional(User user) {
        // 使用Optional：更简洁安全
        return Optional.ofNullable(user)
                      .map(User::getAddress)
                      .map(Address::getCity)
                      .orElse("未知城市");
    }
    
    public static void example8_AvoidNullPointer() {
        System.out.println("\n=== 示例8：避免空指针异常 ===");
        
        // 创建测试数据
        Address address = new Address("北京");
        User user = new User(address);
        User nullUser = null;
        
        System.out.println("传统方式 - 正常用户：" + getCityTraditional(user));
        System.out.println("传统方式 - null用户：" + getCityTraditional(nullUser));
        
        System.out.println("Optional方式 - 正常用户：" + getCityWithOptional(user));
        System.out.println("Optional方式 - null用户：" + getCityWithOptional(nullUser));
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 Optional示例 ==========\n");
        
        example1_CreateOptional();
        example2_CheckValue();
        example3_GetValue();
        example4_ConditionalProcessing();
        example5_Transform();
        example6_Filter();
        example7_RealWorldUsage();
        example8_AvoidNullPointer();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }

    // 辅助类用于示例8
    static class User {
        private Address address;
        
        public User(Address address) {
            this.address = address;
        }
        
        public Address getAddress() {
            return address;
        }
    }
    
    static class Address {
        private String city;
        
        public Address(String city) {
            this.city = city;
        }
        
        public String getCity() {
            return city;
        }
    }
}

