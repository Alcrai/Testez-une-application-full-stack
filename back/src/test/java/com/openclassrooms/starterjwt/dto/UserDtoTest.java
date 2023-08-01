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

class UserDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSUserDto() {
        LocalDateTime date = LocalDateTime.now();
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@email.com");
        userDto.setLastName("Lastname");
        userDto.setFirstName("FirstName");
        userDto.setAdmin(true);
        userDto.setPassword("password");
        userDto.setCreatedAt(date);
        userDto.setUpdatedAt(date);

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUserDto() {
        UserDto userDto = new UserDto();

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertEquals(0, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        assertEquals(userDto1, userDto1);
        assertEquals(userDto1, userDto2);
        assertEquals(userDto1.hashCode(), userDto2.hashCode());

        // Test for non-equality
        userDto1.setId(1L);
        userDto2.setId(2L);

        assertEquals(userDto1, userDto1);
        assertEquals(userDto2, userDto2);
        assertEquals(userDto1.hashCode(), userDto1.hashCode());
        assertEquals(userDto2.hashCode(), userDto2.hashCode());
        assertTrue(!userDto1.equals(userDto2));
    }

}