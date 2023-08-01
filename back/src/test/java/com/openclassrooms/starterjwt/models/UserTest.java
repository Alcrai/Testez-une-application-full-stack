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

class UserTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSUserDto() {
        LocalDateTime date = LocalDateTime.now();
        User user = new User();
        user.setId(1L);
        user.setEmail("test@email.com");
        user.setLastName("Lastname");
        user.setFirstName("FirstName");
        user.setAdmin(true);
        user.setPassword("password");
        user.setCreatedAt(date);
        user.setUpdatedAt(date);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUserDto() {
        User user = new User();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User();
        User user2 = new User();

        assertEquals(user1, user1);
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        // Test for non-equality
        user1.setId(1L);
        user2.setId(2L);

        assertEquals(user1, user1);
        assertEquals(user2, user2);
        assertEquals(user1.hashCode(), user1.hashCode());
        assertEquals(user2.hashCode(), user2.hashCode());
        assertTrue(!user1.equals(user2));
    }

}