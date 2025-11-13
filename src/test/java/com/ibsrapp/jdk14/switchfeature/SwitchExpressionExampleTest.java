package com.ibsrapp.jdk14.switchfeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK14 Switch表达式单元测试
 */
public class SwitchExpressionExampleTest {

    @Test
    public void testBasicSwitchExpression() {
        String day = "MONDAY";
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
        
        assertEquals(1, dayNumber);
    }

    @Test
    public void testMultipleValues() {
        String type = "apple";
        String category = switch (type) {
            case "apple", "banana", "orange" -> "水果";
            case "carrot", "potato" -> "蔬菜";
            default -> "未知";
        };
        
        assertEquals("水果", category);
    }
}

