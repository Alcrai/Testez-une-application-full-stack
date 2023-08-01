package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseTest {
    @Test
    void messageResponseTest(){
        String message = "response";
        MessageResponse messageResponse = new MessageResponse(message);
        assertEquals(message,messageResponse.getMessage());
        messageResponse.setMessage("response2");
        assertEquals("response2",messageResponse.getMessage());
    }

}