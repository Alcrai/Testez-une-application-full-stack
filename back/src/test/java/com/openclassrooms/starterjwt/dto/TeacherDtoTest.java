package com.openclassrooms.starterjwt.dto;

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

class TeacherDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidTeacherDto() {
        LocalDateTime date = LocalDateTime.now();
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setLastName("LastName");
        teacherDto.setFirstName("FirstName");
        teacherDto.setCreatedAt(date);
        teacherDto.setUpdatedAt(date);

        Set<ConstraintViolation<TeacherDto>> violations = validator.validate(teacherDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidTeacherDto() {
        TeacherDto teacherDto = new TeacherDto();

        Set<ConstraintViolation<TeacherDto>> violations = validator.validate(teacherDto);
        assertEquals(2, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        TeacherDto teacherDto1 = new TeacherDto();
        TeacherDto teacherDto2 = new TeacherDto();

        assertEquals(teacherDto1, teacherDto1);
        assertEquals(teacherDto1, teacherDto2);
        assertEquals(teacherDto1.hashCode(), teacherDto2.hashCode());

        // Test for non-equality
        teacherDto1.setId(1L);
        teacherDto2.setId(2L);

        assertEquals(teacherDto1, teacherDto1);
        assertEquals(teacherDto2, teacherDto2);
        assertEquals(teacherDto1.hashCode(), teacherDto1.hashCode());
        assertEquals(teacherDto2.hashCode(), teacherDto2.hashCode());
        assertTrue(!teacherDto1.equals(teacherDto2));
    }
}