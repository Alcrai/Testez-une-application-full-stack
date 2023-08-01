package com.openclassrooms.starterjwt.payload.response;

import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSignUpRequest() {
        String token = "token";
        Long id = 1l;
        String username = "john.doe";
        String firstName = "John";
        String lastName = "Doe";
        Boolean admin = true;

        JwtResponse jwtResponse = new JwtResponse("",2l,"","","",false);
        jwtResponse.setToken(token);
        jwtResponse.setId(id);
        jwtResponse.setUsername(username);
        jwtResponse.setFirstName(firstName);
        jwtResponse.setLastName(lastName);
        jwtResponse.setAdmin(admin);

        Set<ConstraintViolation<JwtResponse>> violations = validator.validate(jwtResponse);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUserDto() {
        String token = "token";
        Long id = 1l;
        String username = "john.doe";
        String firstName = "John";
        String lastName = "Doe";
        Boolean admin = true;

        JwtResponse jwtResponse = new JwtResponse(token,id,username,firstName,lastName,admin);
        Set<ConstraintViolation<JwtResponse>> violations = validator.validate(jwtResponse);
        assertEquals(0, violations.size());
    }



}