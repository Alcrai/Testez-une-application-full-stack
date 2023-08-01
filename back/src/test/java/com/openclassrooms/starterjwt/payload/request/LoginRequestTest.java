package com.openclassrooms.starterjwt.payload.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {
    @Test
    void loginRequestTest(){
        String email = "test@test.com";
        String password = "password";

        LoginRequest loginRequest = new LoginRequest(email,password);

        assertEquals("test@test.com",loginRequest.getEmail());
        assertEquals("password", loginRequest.getPassword());

        //cas avec les setters
        loginRequest.setEmail("test1@test.com");
        loginRequest.setPassword("password1");
        assertEquals("test1@test.com",loginRequest.getEmail());
        assertEquals("password1", loginRequest.getPassword());

    }

}