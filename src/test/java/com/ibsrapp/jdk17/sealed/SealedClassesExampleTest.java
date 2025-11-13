package com.ibsrapp.jdk17.sealed;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK17 Sealed Classes单元测试
 */
public class SealedClassesExampleTest {

    @Test
    public void testSealedInterface() {
        SealedClassesExample.Shape circle = new SealedClassesExample.Circle(5.0);
        SealedClassesExample.Shape rectangle = new SealedClassesExample.Rectangle(4.0, 6.0);
        
        assertTrue(circle instanceof SealedClassesExample.Circle);
        assertTrue(rectangle instanceof SealedClassesExample.Rectangle);
        
        double circleArea = circle.area();
        double rectangleArea = rectangle.area();
        
        assertTrue(circleArea > 0);
        assertTrue(rectangleArea > 0);
    }

    @Test
    public void testPatternMatchingWithSealed() {
        SealedClassesExample.Shape shape = new SealedClassesExample.Circle(3.0);
        
        String description = switch (shape) {
            case SealedClassesExample.Circle c -> "圆形";
            case SealedClassesExample.Rectangle r -> "矩形";
            case SealedClassesExample.Triangle t -> "三角形";
        };
        
        assertEquals("圆形", description);
    }
}

