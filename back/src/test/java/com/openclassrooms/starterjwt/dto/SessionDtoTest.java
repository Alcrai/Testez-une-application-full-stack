package com.openclassrooms.starterjwt.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSessionDto() {
        LocalDateTime date = LocalDateTime.now();
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(1L);
        sessionDto.setName("Test SessionDto");
        sessionDto.setDate(new Date());
        sessionDto.setTeacher_id(2L);
        sessionDto.setDescription("This is a test session description.");
        sessionDto.setUsers(List.of(3L, 4L));
        sessionDto.setCreatedAt(date);
        sessionDto.setUpdatedAt(date);

        Set<ConstraintViolation<SessionDto>> violations = validator.validate(sessionDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidSessionDto() {
        SessionDto sessionDto = new SessionDto();

        Set<ConstraintViolation<SessionDto>> violations = validator.validate(sessionDto);
        assertEquals(4, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        SessionDto sessionDto1 = new SessionDto();
        SessionDto sessionDto2 = new SessionDto();

        assertEquals(sessionDto1, sessionDto1);
        assertEquals(sessionDto1, sessionDto2);
        assertEquals(sessionDto1.hashCode(), sessionDto2.hashCode());

        // Test for non-equality
        sessionDto1.setId(1L);
        sessionDto2.setId(2L);

        assertEquals(sessionDto1, sessionDto1);
        assertEquals(sessionDto2, sessionDto2);
        assertEquals(sessionDto1.hashCode(), sessionDto1.hashCode());
        assertEquals(sessionDto2.hashCode(), sessionDto2.hashCode());
        assertTrue(!sessionDto1.equals(sessionDto2));
    }

}