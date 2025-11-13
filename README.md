# JDK 特性学习项目

本项目用于深入学习从 JDK8 到 JDK25 中新增的语法特性和核心功能。

## 项目结构

```
learnJDK/
├── pom.xml                          # Maven配置文件
├── README.md                        # 项目说明文档
└── src/
    ├── main/java/com/ibsrapp/
    │   ├── jdk8/                    # JDK8 新特性
    │   ├── jdk9/                    # JDK9 新特性
    │   ├── jdk10/                   # JDK10 新特性
    │   └── ...                      # 后续版本
    └── test/java/com/ibsrapp/
        ├── jdk8/                    # JDK8 测试类
        ├── jdk9/                    # JDK9 测试类
        ├── jdk10/                   # JDK10 测试类
        └── ...                      # 后续版本测试
```

## 特性索引

### JDK 8 新特性

1. **Lambda 表达式** (`jdk8/lambda/`)
   - Lambda 表达式语法和基本使用
   - 函数式接口（Predicate、Function、Consumer、Supplier等）
   - 自定义函数式接口
   - Lambda表达式中的变量作用域

2. **Stream API** (`jdk8/stream/`)
   - Stream 创建方式（stream()、of()、generate()、iterate()）
   - 中间操作（filter、map、flatMap、sorted、distinct、limit、skip）
   - 终端操作（forEach、collect、reduce、count、anyMatch、allMatch、noneMatch）
   - 分组和分区（groupingBy、partitioningBy）
   - 并行流处理

3. **Optional** (`jdk8/optional/`)
   - Optional 创建（of()、ofNullable()、empty()）
   - 值检查和获取（isPresent()、isEmpty()、get()、orElse()、orElseGet()、orElseThrow()）
   - 条件处理（ifPresent()、ifPresentOrElse()）
   - 转换操作（map()、flatMap()）
   - 过滤操作（filter()）
   - 实际应用场景和避免空指针异常

4. **方法引用** (`jdk8/methodreference/`)
   - 静态方法引用（类名::静态方法名）
   - 实例方法引用（实例::实例方法名）
   - 类的实例方法引用（类名::实例方法名）
   - 构造器引用（类名::new）
   - 方法引用 vs Lambda表达式

5. **接口默认方法和静态方法** (`jdk8/interfacefeature/`)
   - 接口默认方法（default关键字）
   - 接口静态方法
   - 多重继承问题解决
   - 实际应用场景

6. **新的日期时间 API** (`jdk8/datetime/`)
   - LocalDate、LocalTime、LocalDateTime
   - ZonedDateTime（时区处理）
   - Instant（时间戳）
   - Period（日期间隔）和 Duration（时间间隔）
   - DateTimeFormatter（日期格式化）
   - TemporalAdjusters（日期调整器）

7. **CompletableFuture** (`jdk8/concurrent/`)
   - 异步任务创建（runAsync()、supplyAsync()）
   - 结果处理（thenApply()、thenAccept()、thenRun()）
   - 组合操作（thenCompose()、thenCombine()、allOf()）
   - 异常处理（exceptionally()、handle()）
   - 异步回调（whenComplete()、thenApplyAsync()）
   - 超时处理（orTimeout()、completeOnTimeout()）

### JDK 9 新特性

1. **接口私有方法** (`jdk9/interfacefeature/`)
   - 接口私有实例方法
   - 接口私有静态方法
   - 在默认方法中共享逻辑
   - 实际应用场景

2. **集合工厂方法** (`jdk9/collection/`)
   - List.of() - 创建不可变列表
   - Set.of() - 创建不可变集合
   - Map.of() - 创建不可变映射（最多10个键值对）
   - Map.ofEntries() - 创建包含更多键值对的映射
   - 与JDK8之前方式的对比

3. **Stream API 增强** (`jdk9/stream/`)
   - takeWhile() - 从流开始处取元素直到条件不满足
   - dropWhile() - 从流开始处丢弃元素直到条件不满足
   - ofNullable() - 创建包含单个元素或空的流
   - iterate()增强 - 支持带条件的迭代

4. **Optional 增强** (`jdk9/optional/`)
   - ifPresentOrElse() - 如果值存在则执行一个操作，否则执行另一个操作
   - or() - 如果值不存在，则返回另一个Optional
   - 实际应用场景（配置查找、用户认证等）

### JDK 10 新特性

1. **局部变量类型推断** (`jdk10/vartypeinference/`)
   - var 关键字基本使用
   - 简化泛型类型声明
   - 在循环中使用var
   - 与Lambda表达式结合
   - var的限制和最佳实践

2. **集合copyOf()方法** (`jdk10/collection/`)
   - List.copyOf() - 创建不可变列表副本
   - Set.copyOf() - 创建不可变集合副本
   - Map.copyOf() - 创建不可变映射副本
   - 作为方法返回值保护数据安全
   - 与JDK9工厂方法的对比

### JDK 11 新特性

1. **字符串增强** (`jdk11/string/`)
   - strip()、stripLeading()、stripTrailing() - 去除空白字符
   - isBlank() - 判断是否为空或只包含空白字符
   - lines() - 按行分割成Stream
   - repeat() - 重复字符串指定次数

2. **Files增强** (`jdk11/files/`)
   - readString() - 读取文件内容为字符串
   - writeString() - 将字符串写入文件

3. **Lambda表达式增强** (`jdk11/lambda/`)
   - var在Lambda参数中使用

4. **Optional增强** (`jdk11/optional/`)
   - isEmpty() - 检查Optional是否为空

### JDK 14 新特性

1. **Switch表达式** (`jdk14/switchfeature/`)
   - 箭头语法（->）
   - 多值匹配（case "a", "b":）
   - yield关键字返回值
   - 作为表达式使用

2. **Pattern Matching for instanceof** (`jdk14/patternmatching/`)
   - 在instanceof检查时直接声明变量
   - 自动类型转换
   - 简化类型检查代码

### JDK 15 新特性

1. **Text Blocks（文本块）** (`jdk15/textblocks/`)
   - 三重引号（"""）定义多行字符串
   - 自动处理缩进和换行
   - 格式化字符串（formatted方法）
   - 转义字符支持

### JDK 16 新特性

1. **Records（记录类）** (`jdk16/records/`)
   - record关键字定义数据载体类
   - 自动生成标准方法（构造函数、getter、equals、hashCode、toString）
   - 自定义方法和构造函数
   - 嵌套Records

### JDK 17 新特性

1. **Sealed Classes（密封类）** (`jdk17/sealed/`)
   - sealed关键字限制继承
   - permits指定允许的子类
   - 与Pattern Matching结合使用
   - 实现状态机

### JDK 21 新特性

1. **虚拟线程（Virtual Threads）** (`jdk21/virtualthreads/`)
   - Thread.ofVirtual()创建虚拟线程
   - Executors.newVirtualThreadPerTaskExecutor()
   - 高并发I/O操作
   - 轻量级线程管理

2. **Sequenced Collections（有序集合）** (`jdk21/sequencedcollections/`)
   - SequencedCollection、SequencedSet、SequencedMap接口
   - getFirst()、getLast()方法
   - addFirst()、addLast()方法
   - reversed()方法获取反向视图

### JDK 22 新特性

1. **String Templates（字符串模板）** (`jdk22/stringtemplates/`)
   - STR模板处理器
   - \{variable}语法进行插值
   - 编译时验证
   - 防止注入攻击
   - 注意：这是预览特性

### JDK 23 新特性

1. **API增强和性能优化** (`jdk23/features/`)
   - Stream API增强
   - Collections API改进
   - 性能优化
   - 注意：JDK23主要特性集中在API增强和性能优化上，语法层面的新特性相对较少

### JDK 24 新特性

1. **API增强和性能优化** (`jdk24/features/`)
   - API增强
   - 性能优化
   - 工具改进
   - 注意：JDK24主要特性集中在API增强和性能优化上

### JDK 25 新特性

1. **最新版本特性** (`jdk25/features/`)
   - 性能优化和JVM改进
   - API增强和完善
   - 对现有特性的优化
   - 现代Java特性综合应用
   - 注意：JDK25是最新版本，主要特性集中在性能优化和API增强上，语法层面的重大新特性相对较少

## 使用说明

### 编译项目

```bash
mvn clean compile
```

### 运行测试

```bash
mvn test
```

### 运行特定版本的示例

每个特性类都包含 `main` 方法，可以直接运行：

```bash
# 编译
mvn compile

# 运行特定类
java -cp target/classes com.ibsrapp.jdk8.lambda.LambdaExample
```

## 开发环境要求

- JDK 25+ (项目使用JDK25编译，代码展示JDK8-25的特性)
- Maven 3.6+

### 环境配置

确保系统已安装JDK25，并设置JAVA_HOME环境变量：

```bash
# macOS/Linux
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home

# 验证JDK版本
java -version
mvn -version
```

## 注意事项

- 本项目仅包含正式发布的特性，不包括实验性特性
- 每个特性都有详细的中文注释和使用说明
- 所有示例代码都包含可运行的 main 方法和对应的单元测试

