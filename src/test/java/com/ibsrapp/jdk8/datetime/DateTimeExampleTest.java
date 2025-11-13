package com.ibsrapp.jdk8.datetime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * JDK8 新的日期时间API单元测试
 */
public class DateTimeExampleTest {

    @Test
    public void testLocalDate() {
        LocalDate date = LocalDate.of(2024, 12, 25);
        
        assertEquals(2024, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(25, date.getDayOfMonth());
    }

    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.of(14, 30, 45);
        
        assertEquals(14, time.getHour());
        assertEquals(30, time.getMinute());
        assertEquals(45, time.getSecond());
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 10, 30);
        
        assertEquals(2024, dateTime.getYear());
        assertEquals(12, dateTime.getMonthValue());
        assertEquals(25, dateTime.getDayOfMonth());
        assertEquals(10, dateTime.getHour());
        assertEquals(30, dateTime.getMinute());
    }

    @Test
    public void testDateCalculation() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        
        LocalDate tomorrow = date.plusDays(1);
        assertEquals(2, tomorrow.getDayOfMonth());
        
        LocalDate nextMonth = date.plusMonths(1);
        assertEquals(2, nextMonth.getMonthValue());
    }

    @Test
    public void testFormatting() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 25, 10, 30, 0);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = dateTime.format(formatter);
        
        assertEquals("2024-12-25 10:30:00", formatted);
    }

    @Test
    public void testParsing() {
        String dateStr = "2024-12-25";
        LocalDate date = LocalDate.parse(dateStr);
        
        assertEquals(2024, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(25, date.getDayOfMonth());
    }
}

