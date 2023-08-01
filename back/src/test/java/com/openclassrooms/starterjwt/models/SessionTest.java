package com.openclassrooms.starterjwt.models;

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

class SessionTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSessionDto() {
        LocalDateTime date = LocalDateTime.now();
        Session session = new Session();
        session.setId(1L);
        session.setName("Test SessionDto");
        session.setDate(new Date());
        session.setDescription("This is a test session description.");
        session.setTeacher(new Teacher());
        session.setUsers(List.of(new User()));
        session.setCreatedAt(date);
        session.setUpdatedAt(date);

        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidSessionDto() {
        Session session = new Session();

        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertEquals(3, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        Session session1 = new Session();
        Session session2 = new Session();

        assertEquals(session1, session1);
        assertEquals(session1, session2);
        assertEquals(session1.hashCode(), session2.hashCode());

        // Test for non-equality
        session1.setId(1L);
        session2.setId(2L);

        assertEquals(session1, session1);
        assertEquals(session2, session2);
        assertEquals(session1.hashCode(), session1.hashCode());
        assertEquals(session2.hashCode(), session2.hashCode());
        assertTrue(!session1.equals(session2));
    }
}