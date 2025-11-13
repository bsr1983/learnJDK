package com.ibsrapp.jdk8.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * JDK8 方法引用示例
 * 
 * 方法引用是Lambda表达式的一种简化写法，当Lambda表达式只是调用一个已存在的方法时，
 * 可以使用方法引用来替代Lambda表达式，使代码更加简洁。
 * 
 * 方法引用的四种形式：
 * 1. 静态方法引用：类名::静态方法名
 * 2. 实例方法引用：实例::实例方法名
 * 3. 类的实例方法引用：类名::实例方法名
 * 4. 构造器引用：类名::new
 */
public class MethodReferenceExample {

    /**
     * 示例1：静态方法引用
     * 引用类的静态方法
     */
    public static void example1_StaticMethodReference() {
        System.out.println("=== 示例1：静态方法引用 ===");
        
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
        
        // Lambda表达式方式
        List<Integer> parsed1 = numbers.stream()
                                      .map(s -> Integer.parseInt(s))
                                      .toList();
        
        // 静态方法引用方式（更简洁）
        List<Integer> parsed2 = numbers.stream()
                                      .map(Integer::parseInt)
                                      .toList();
        
        System.out.println("Lambda方式：" + parsed1);
        System.out.println("方法引用方式：" + parsed2);
        
        // 另一个例子：使用Math.max
        BiFunction<Integer, Integer, Integer> max1 = (a, b) -> Math.max(a, b);
        BiFunction<Integer, Integer, Integer> max2 = Math::max;
        
        System.out.println("max(10, 20) = " + max2.apply(10, 20));
    }

    /**
     * 示例2：实例方法引用
     * 引用特定对象的实例方法
     */
    public static void example2_InstanceMethodReference() {
        System.out.println("\n=== 示例2：实例方法引用 ===");
        
        String prefix = "用户：";
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Lambda表达式方式
        names.forEach(name -> System.out.println(prefix + name));
        
        // 实例方法引用方式
        names.forEach(System.out::println);
        
        // 使用自定义对象的实例方法
        StringBuilder sb = new StringBuilder();
        names.forEach(sb::append);
        System.out.println("拼接结果：" + sb.toString());
    }

    /**
     * 示例3：类的实例方法引用
     * 引用类的实例方法，第一个参数作为方法调用的对象
     */
    public static void example3_ClassInstanceMethodReference() {
        System.out.println("\n=== 示例3：类的实例方法引用 ===");
        
        List<String> words = Arrays.asList("hello", "world", "java", "lambda");
        
        // Lambda表达式方式
        List<String> upper1 = words.stream()
                                   .map(s -> s.toUpperCase())
                                   .toList();
        
        // 类的实例方法引用方式
        List<String> upper2 = words.stream()
                                   .map(String::toUpperCase)
                                   .toList();
        
        System.out.println("Lambda方式：" + upper1);
        System.out.println("方法引用方式：" + upper2);
        
        // 另一个例子：字符串比较
        List<String> sorted = words.stream()
                                   .sorted(String::compareToIgnoreCase)
                                   .toList();
        System.out.println("排序后：" + sorted);
    }

    /**
     * 示例4：构造器引用
     * 引用类的构造器
     */
    public static void example4_ConstructorReference() {
        System.out.println("\n=== 示例4：构造器引用 ===");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Lambda表达式方式
        List<Person> persons1 = names.stream()
                                     .map(name -> new Person(name))
                                     .toList();
        
        // 构造器引用方式
        List<Person> persons2 = names.stream()
                                     .map(Person::new)
                                     .toList();
        
        System.out.println("Lambda方式创建的对象数量：" + persons1.size());
        System.out.println("构造器引用方式创建的对象数量：" + persons2.size());
        
        // 使用Supplier接口
        Supplier<Person> personSupplier1 = () -> new Person("Default");
        Supplier<Person> personSupplier2 = Person::new; // 无参构造器
        
        Person defaultPerson = personSupplier2.get();
        System.out.println("默认人员：" + defaultPerson.getName());
    }

    /**
     * 示例5：方法引用 vs Lambda表达式
     * 对比两种写法的使用场景
     */
    public static void example5_Comparison() {
        System.out.println("\n=== 示例5：方法引用 vs Lambda表达式 ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // 场景1：简单方法调用 - 使用方法引用更简洁
        numbers.forEach(System.out::println); // 方法引用
        numbers.forEach(n -> System.out.println(n)); // Lambda表达式
        
        // 场景2：需要额外操作 - 使用Lambda表达式
        numbers.forEach(n -> System.out.println("数字：" + n)); // Lambda表达式更合适
        
        // 场景3：静态方法调用
        Function<String, Integer> parseInt1 = s -> Integer.parseInt(s);
        Function<String, Integer> parseInt2 = Integer::parseInt; // 方法引用更简洁
        
        System.out.println("parseInt(\"123\") = " + parseInt2.apply("123"));
    }

    /**
     * 示例6：复杂的方法引用场景
     * 方法引用在Stream API中的广泛应用
     */
    public static void example6_ComplexScenarios() {
        System.out.println("\n=== 示例6：复杂的方法引用场景 ===");
        
        List<Person> persons = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 28)
        );
        
        // 提取姓名
        List<String> names = persons.stream()
                                   .map(Person::getName) // 方法引用
                                   .toList();
        System.out.println("姓名列表：" + names);
        
        // 按年龄排序
        List<Person> sorted = persons.stream()
                                    .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                                    .toList();
        System.out.println("按年龄排序：" + sorted);
        
        // 使用方法引用进行排序（需要Comparator.comparing）
        List<Person> sorted2 = persons.stream()
                                      .sorted(java.util.Comparator.comparing(Person::getAge))
                                      .toList();
        System.out.println("使用方法引用排序：" + sorted2);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 方法引用示例 ==========\n");
        
        example1_StaticMethodReference();
        example2_InstanceMethodReference();
        example3_ClassInstanceMethodReference();
        example4_ConstructorReference();
        example5_Comparison();
        example6_ComplexScenarios();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }

    // 辅助类
    static class Person {
        private String name;
        private int age;
        
        public Person(String name) {
            this.name = name;
            this.age = 0;
        }
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public Person() {
            this.name = "Default";
            this.age = 0;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}

