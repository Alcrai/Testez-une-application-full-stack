package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeacherTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidTeacherDto() {
        LocalDateTime date = LocalDateTime.now();
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("LastName");
        teacher.setFirstName("FirstName");
        teacher.setCreatedAt(date);
        teacher.setUpdatedAt(date);

        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidTeacherDto() {
        Teacher teacher = new Teacher();

        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertEquals(2, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();

        assertEquals(teacher1, teacher1);
        assertEquals(teacher1, teacher2);
        assertEquals(teacher1.hashCode(), teacher2.hashCode());

        // Test for non-equality
        teacher1.setId(1L);
        teacher2.setId(2L);

        assertEquals(teacher1, teacher1);
        assertEquals(teacher2, teacher2);
        assertEquals(teacher1.hashCode(), teacher1.hashCode());
        assertEquals(teacher2.hashCode(), teacher2.hashCode());
        assertTrue(!teacher1.equals(teacher2));
    }

}