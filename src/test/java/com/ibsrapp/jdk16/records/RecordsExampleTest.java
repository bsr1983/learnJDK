package com.ibsrapp.jdk16.records;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JDK16 Records单元测试
 */
public class RecordsExampleTest {

    @Test
    public void testBasicRecord() {
        RecordsExample.Person person = new RecordsExample.Person("Alice", 25);
        
        assertEquals("Alice", person.name());
        assertEquals(25, person.age());
        assertNotNull(person.toString());
    }

    @Test
    public void testRecordEquals() {
        RecordsExample.Person person1 = new RecordsExample.Person("Alice", 25);
        RecordsExample.Person person2 = new RecordsExample.Person("Alice", 25);
        
        assertEquals(person1, person2);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testCustomConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RecordsExample.Email("invalid");
        });
        
        RecordsExample.Email email = new RecordsExample.Email("test@example.com");
        assertEquals("test@example.com", email.address());
    }
}

