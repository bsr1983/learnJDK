package com.ibsrapp.jdk8.interfacefeature;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK8 接口默认方法和静态方法单元测试
 */
public class InterfaceDefaultMethodExampleTest {

    @Test
    public void testDefaultMethod() {
        InterfaceDefaultMethodExample.Vehicle car = new InterfaceDefaultMethodExample.Car();
        car.start();
        // stop()是默认方法，应该可以调用
        assertDoesNotThrow(() -> car.stop());
    }

    @Test
    public void testStaticMethod() {
        // 测试接口的静态方法
        int sum = InterfaceDefaultMethodExample.Calculator.add(10, 20);
        assertEquals(30, sum);
        
        int product = InterfaceDefaultMethodExample.Calculator.multiply(5, 6);
        assertEquals(30, product);
    }

    @Test
    public void testMultipleInheritance() {
        InterfaceDefaultMethodExample.Duck1 duck1 = new InterfaceDefaultMethodExample.Duck1();
        assertDoesNotThrow(() -> duck1.move());
        
        InterfaceDefaultMethodExample.Duck2 duck2 = new InterfaceDefaultMethodExample.Duck2();
        assertDoesNotThrow(() -> duck2.move());
    }
}

