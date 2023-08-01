package com.openclassrooms.starterjwt.payload.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignupRequestTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSignUpRequest() {
        String email = "test@test.com";
        String firstName = "John";
        String lastName = "Doe";
        String password = "password";

        SignupRequest request = new SignupRequest("","","","");
        request.setEmail(email);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPassword(password);

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUserDto() {
        String email = "test@test.com";
        String firstName = "John";
        String lastName = "Doe";
        String password = "password";

        SignupRequest request = new SignupRequest(email,firstName,lastName,password);
        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(request);
        assertEquals(0, violations.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        SignupRequest request1 = new SignupRequest("email","firstName","lastName","password");
        SignupRequest request2 = new SignupRequest("email","firstName","lastName","password");

        assertEquals(request1, request1);
        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());

        // Test for non-equality
        request1.setEmail("mail1");
        request2.setEmail("mail2");

        assertEquals(request1, request1);
        assertEquals(request2, request2);
        assertEquals(request1.hashCode(), request1.hashCode());
        assertEquals(request2.hashCode(), request2.hashCode());
        assertTrue(!request1.equals(request2));
    }


}