package com.ibsrapp.jdk9.interfacefeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK9 接口私有方法单元测试
 */
public class InterfacePrivateMethodExampleTest {

    @Test
    public void testPrivateMethodInInterface() {
        InterfacePrivateMethodExample.Calculator calc = 
            new InterfacePrivateMethodExample.SimpleCalculator();
        
        assertEquals(30, calc.add(10, 20));
        assertEquals(30, calc.multiply(5, 6));
        
        assertThrows(IllegalArgumentException.class, () -> {
            calc.add(-1, 5);
        });
    }

    @Test
    public void testStaticPrivateMethod() {
        String result = InterfacePrivateMethodExample.StringProcessor.process("  hello  ");
        assertEquals("HELLO", result);
    }
}

