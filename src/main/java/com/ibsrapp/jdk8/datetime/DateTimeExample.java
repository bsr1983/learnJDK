package com.ibsrapp.jdk8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * JDK8 新的日期时间API示例
 * 
 * JDK8引入了全新的日期时间API（java.time包），解决了旧API（java.util.Date、Calendar等）的诸多问题：
 * 1. 线程安全：所有类都是不可变的
 * 2. 清晰的API设计：方法名直观易懂
 * 3. 时区支持：更好的时区处理
 * 4. 不可变性：所有操作都返回新对象
 * 
 * 主要类：
 * - LocalDate：日期（年月日）
 * - LocalTime：时间（时分秒）
 * - LocalDateTime：日期时间
 * - ZonedDateTime：带时区的日期时间
 * - Instant：时间戳
 * - Period：日期之间的间隔
 * - Duration：时间之间的间隔
 */
public class DateTimeExample {

    /**
     * 示例1：LocalDate - 日期操作
     * LocalDate表示日期，不包含时间信息
     */
    public static void example1_LocalDate() {
        System.out.println("=== 示例1：LocalDate日期操作 ===");
        
        // 获取当前日期
        LocalDate today = LocalDate.now();
        System.out.println("今天：" + today);
        
        // 创建指定日期
        LocalDate date1 = LocalDate.of(2024, 1, 15);
        System.out.println("指定日期：" + date1);
        
        // 解析字符串
        LocalDate date2 = LocalDate.parse("2024-12-25");
        System.out.println("解析日期：" + date2);
        
        // 获取日期的各个部分
        System.out.println("年份：" + today.getYear());
        System.out.println("月份：" + today.getMonth());
        System.out.println("月份值：" + today.getMonthValue());
        System.out.println("日期：" + today.getDayOfMonth());
        System.out.println("星期：" + today.getDayOfWeek());
        
        // 日期计算
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        
        System.out.println("明天：" + tomorrow);
        System.out.println("下周：" + nextWeek);
        System.out.println("下月：" + nextMonth);
        System.out.println("明年：" + nextYear);
    }

    /**
     * 示例2：LocalTime - 时间操作
     * LocalTime表示时间，不包含日期信息
     */
    public static void example2_LocalTime() {
        System.out.println("\n=== 示例2：LocalTime时间操作 ===");
        
        // 获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println("当前时间：" + now);
        
        // 创建指定时间
        LocalTime time1 = LocalTime.of(14, 30, 45);
        System.out.println("指定时间：" + time1);
        
        // 解析字符串
        LocalTime time2 = LocalTime.parse("09:15:30");
        System.out.println("解析时间：" + time2);
        
        // 获取时间的各个部分
        System.out.println("小时：" + now.getHour());
        System.out.println("分钟：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());
        System.out.println("纳秒：" + now.getNano());
        
        // 时间计算
        LocalTime later = now.plusHours(2);
        LocalTime earlier = now.minusMinutes(30);
        
        System.out.println("2小时后：" + later);
        System.out.println("30分钟前：" + earlier);
    }

    /**
     * 示例3：LocalDateTime - 日期时间操作
     * LocalDateTime包含日期和时间信息
     */
    public static void example3_LocalDateTime() {
        System.out.println("\n=== 示例3：LocalDateTime日期时间操作 ===");
        
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间：" + now);
        
        // 创建指定日期时间
        LocalDateTime dateTime1 = LocalDateTime.of(2024, 12, 25, 10, 30, 0);
        System.out.println("指定日期时间：" + dateTime1);
        
        // 从LocalDate和LocalTime组合
        LocalDate date = LocalDate.of(2024, 1, 1);
        LocalTime time = LocalTime.of(12, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(date, time);
        System.out.println("组合日期时间：" + dateTime2);
        
        // 日期时间计算
        LocalDateTime future = now.plusDays(10).plusHours(5);
        System.out.println("10天5小时后：" + future);
        
        // 转换为LocalDate和LocalTime
        LocalDate datePart = now.toLocalDate();
        LocalTime timePart = now.toLocalTime();
        System.out.println("日期部分：" + datePart);
        System.out.println("时间部分：" + timePart);
    }

    /**
     * 示例4：ZonedDateTime - 时区操作
     * ZonedDateTime包含时区信息
     */
    public static void example4_ZonedDateTime() {
        System.out.println("\n=== 示例4：ZonedDateTime时区操作 ===");
        
        // 获取当前时区的日期时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("当前时区时间：" + now);
        
        // 指定时区
        ZonedDateTime beijing = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime newYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));
        
        System.out.println("北京时间：" + beijing);
        System.out.println("纽约时间：" + newYork);
        System.out.println("伦敦时间：" + london);
        
        // 时区转换
        ZonedDateTime converted = beijing.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("转换为UTC：" + converted);
    }

    /**
     * 示例5：Instant - 时间戳
     * Instant表示时间线上的一个点，常用于时间戳
     */
    public static void example5_Instant() {
        System.out.println("\n=== 示例5：Instant时间戳 ===");
        
        // 获取当前时间戳
        Instant now = Instant.now();
        System.out.println("当前时间戳：" + now);
        
        // 从秒数创建
        Instant instant1 = Instant.ofEpochSecond(1609459200); // 2021-01-01 00:00:00 UTC
        System.out.println("指定时间戳：" + instant1);
        
        // 从毫秒数创建
        Instant instant2 = Instant.ofEpochMilli(1609459200000L);
        System.out.println("从毫秒创建：" + instant2);
        
        // 转换为LocalDateTime（需要指定时区）
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        System.out.println("转换为本地时间：" + localDateTime);
        
        // 时间戳计算
        Instant future = now.plus(1, ChronoUnit.DAYS);
        System.out.println("1天后：" + future);
    }

    /**
     * 示例6：Period和Duration - 时间间隔
     * Period用于日期之间的间隔，Duration用于时间之间的间隔
     */
    public static void example6_PeriodAndDuration() {
        System.out.println("\n=== 示例6：Period和Duration时间间隔 ===");
        
        // Period：日期间隔
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 12, 31);
        Period period = Period.between(date1, date2);
        System.out.println("日期间隔：" + period);
        System.out.println("间隔天数：" + period.getDays());
        System.out.println("间隔月数：" + period.getMonths());
        System.out.println("间隔年数：" + period.getYears());
        
        // Duration：时间间隔
        LocalTime time1 = LocalTime.of(10, 0);
        LocalTime time2 = LocalTime.of(14, 30);
        Duration duration = Duration.between(time1, time2);
        System.out.println("时间间隔：" + duration);
        System.out.println("间隔小时数：" + duration.toHours());
        System.out.println("间隔分钟数：" + duration.toMinutes());
        
        // 使用ChronoUnit计算
        long days = ChronoUnit.DAYS.between(date1, date2);
        long hours = ChronoUnit.HOURS.between(time1, time2);
        System.out.println("天数差：" + days);
        System.out.println("小时差：" + hours);
    }

    /**
     * 示例7：日期格式化
     * 使用DateTimeFormatter格式化日期时间
     */
    public static void example7_Formatting() {
        System.out.println("\n=== 示例7：日期格式化 ===");
        
        LocalDateTime now = LocalDateTime.now();
        
        // 预定义的格式化器
        System.out.println("ISO格式：" + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        // 自定义格式化器
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("自定义格式1：" + now.format(formatter1));
        
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println("自定义格式2：" + now.format(formatter2));
        
        // 解析字符串
        String dateStr = "2024-12-25 10:30:00";
        LocalDateTime parsed = LocalDateTime.parse(dateStr, formatter1);
        System.out.println("解析结果：" + parsed);
    }

    /**
     * 示例8：日期调整器
     * 使用TemporalAdjusters进行复杂的日期调整
     */
    public static void example8_TemporalAdjusters() {
        System.out.println("\n=== 示例8：日期调整器 ===");
        
        LocalDate date = LocalDate.of(2024, 3, 15);
        System.out.println("原始日期：" + date);
        
        // 获取月份的第一天
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("本月第一天：" + firstDay);
        
        // 获取月份的最后一天
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月最后一天：" + lastDay);
        
        // 获取下个月的第一天
        LocalDate nextMonthFirst = date.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("下个月第一天：" + nextMonthFirst);
        
        // 获取下一个星期一
        LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("下一个星期一：" + nextMonday);
        
        // 获取本月的最后一个星期五
        LocalDate lastFriday = date.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        System.out.println("本月最后一个星期五：" + lastFriday);
    }

    /**
     * 主方法：运行所有示例
     */
    public static void main(String[] args) {
        System.out.println("========== JDK8 新的日期时间API示例 ==========\n");
        
        example1_LocalDate();
        example2_LocalTime();
        example3_LocalDateTime();
        example4_ZonedDateTime();
        example5_Instant();
        example6_PeriodAndDuration();
        example7_Formatting();
        example8_TemporalAdjusters();
        
        System.out.println("\n========== 示例运行完成 ==========");
    }
}

